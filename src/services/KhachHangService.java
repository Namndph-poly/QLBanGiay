/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.KhachHang;

/**
 *
 * @author Admin
 */
public interface KhachHangService {
    public List<KhachHang> getList();
    public String add(KhachHang kh);
    public String update(KhachHang kh,String Ten);
    public List<KhachHang> search(String sdt);
}
