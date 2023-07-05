/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;
import entity.Bill;
/**
 *
 * @author ADMIN
 */


public interface BillManagement {
    void createBill(Bill bill);
    Bill readBill(int billId);
    void updateBill(int billId, Bill updatedBill);
    void deleteBill(int billId);
}