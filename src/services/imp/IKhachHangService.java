/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.imp;

import java.util.List;
import models.KhachHang;
import repositorys.KhachHangRepository;
import services.KhachHangService;

/**
 *
 * @author Admin
 */
public class IKhachHangService implements KhachHangService {

    private KhachHangRepository khRepo;

    public IKhachHangService() {
        this.khRepo = new KhachHangRepository();
    }

    @Override
    public List<KhachHang> getList() {
        return this.khRepo.getList();
    }

    @Override
    public String add(KhachHang kh) {
        boolean add = this.khRepo.add(kh);
        if (add) {
            return "Them thanh cong";
        } else {
            return "Them that  bai";
        }
    }

    @Override
    public String update(KhachHang kh, String Ten) {
        boolean add = this.khRepo.update(kh, Ten);
        if (add) {
            return "Sua thanh cong";
        } else {
            return "Sua that  bai";
        }
    }

    @Override
    public List<KhachHang> search(String sdt) {
        return khRepo.search(sdt);
    }

}
