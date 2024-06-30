package com.example.vehiclestore.business.services;

import java.util.List;

import com.example.vehiclestore.DAO.entities.PurchaseOrder;

public interface PurchaseOrderService {

    public List<PurchaseOrder> getAllPurchaseOrders();

    public PurchaseOrder getPurchaseOrderById(Long id);

    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);

    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder);

    public void deletePurchaseOrder(Long id);
}