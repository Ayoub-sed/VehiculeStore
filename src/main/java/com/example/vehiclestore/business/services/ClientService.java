package com.example.vehiclestore.business.services;

import com.example.vehiclestore.DAO.entities.Client;

import java.util.List;


public interface ClientService {

   
    public List<Client> getAllClients() ;

    public Client getClientById(Long id);

    public Client createClient(Client client) ;

    public Client updateClient(Long id, Client client);

    public void deleteClient(Long id) ;
}