package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Transactions;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Repository.TransactionsRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.request.ViewWalletRequest;
import com.haui.btl.demo.dto.response.TransactionResponse;
import com.haui.btl.demo.dto.response.ViewWalletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViewWalletService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    public ViewWalletResponse viewWallet(ViewWalletRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isPresent()) {
            User user = userOpt.get();

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
        } else {
            throw new RuntimeException("User not found");
        }
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
}
