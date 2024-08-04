package com.haui.btl.demo.Service;

import com.haui.btl.demo.Config.JwtTokenProvider;
import com.haui.btl.demo.Entity.Transactions;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Repository.TransactionsRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.request.TopUpRequest;
import com.haui.btl.demo.dto.request.WithdrawRequest;
import com.haui.btl.demo.dto.response.TransactionResponse;
import com.haui.btl.demo.dto.response.ViewWalletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViewWalletService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ViewWalletResponse viewWallet() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        List<Transactions> transactionsList = transactionsRepository.findByUserIduser(user.getIduser());

        List<TransactionResponse> transactionResponses = transactionsList.stream()
                .map(this::mapToTransactionResponse)
                .collect(Collectors.toList());

        ViewWalletResponse response = ViewWalletResponse.builder()
                .userId(user.getIduser())
                .walletBalance(user.getWallet())
                .transactions(transactionResponses)
                .build();

        if (user.getWallet() >= 0) {
            response.setInstruction("Please make sure to have sufficient balance when you return the car.");
            response.setPaymentStatus("Current wallet balance: " + user.getWallet());
        } else {
            response.setInstruction("Please top-up your wallet.");
            response.setPaymentStatus("Insufficient balance: " + user.getWallet());
        }

        return response;
    }

    private TransactionResponse mapToTransactionResponse(Transactions transaction) {
        return TransactionResponse.builder()
                .idtransactions(transaction.getIdtransactions())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .datetime(transaction.getDatetime())
                .bookingno(transaction.getBookingno())
                .carname(transaction.getCarname())
                .note(transaction.getNote())
                .build();
    }

    public ViewWalletResponse topUpWallet(TopUpRequest topUpRequest) {
        User user = getCurrentUser();
        user.setWallet(user.getWallet() + topUpRequest.getAmount());

        // Save transaction
        Transactions transaction = new Transactions();
        transaction.setUserIduser(user.getIduser());
        transaction.setAmount(topUpRequest.getAmount());
        transaction.setType("Top-up");
        transaction.setDatetime(LocalDateTime.now());
        transaction.setBookingno("N/A");
        transaction.setCarname("N/A");
        transaction.setNote("Top-up");

        transactionsRepository.save(transaction);
        userRepository.save(user);

        return viewWallet();
    }

    public ViewWalletResponse withdrawFromWallet(WithdrawRequest withdrawRequest) {
        User user = getCurrentUser();
        if (user.getWallet() < withdrawRequest.getAmount()) {
            throw new AppException(ErrorCode.INSUFFICIENT_BALANCE);
        }

        user.setWallet(user.getWallet() - withdrawRequest.getAmount());

        // Save transaction
        Transactions transaction = new Transactions();
        transaction.setUserIduser(user.getIduser());
        transaction.setAmount(withdrawRequest.getAmount());
        transaction.setType("Withdraw");
        transaction.setDatetime(LocalDateTime.now());
        transaction.setBookingno("N/A");
        transaction.setCarname("N/A");
        transaction.setNote("Withdraw");

        transactionsRepository.save(transaction);
        userRepository.save(user);

        return viewWallet();
    }

    private User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
    }
}

