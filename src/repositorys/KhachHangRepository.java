/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.util.List;
import models.KhachHang;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import utilconnext.DBConnection;

/**
 *
 * @author Admin
 */
public class KhachHangRepository {

    private Connection con;

    public KhachHangRepository() {
        this.con = DBConnection.openDbConnection();
    }

    public List<KhachHang> getList() {
        String query = "SELECT \n"
                + "      [Ten]\n"
                + "      ,[TenDem]\n"
                + "      ,[Ho]\n"
                + "      ,[Gioitinh]\n"
                + "      ,[NgaySinh]\n"
                + "      ,[Email]\n"
                + "      ,[Sdt]\n"
                + "      ,[Diemthuong]\n"
                + "  FROM [dbo].[KhachHang]";
        List<KhachHang> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(KhachHang kh) {
        String query = "INSERT INTO [dbo].[KhachHang]\n"
                + "           ([Ten]\n"
                + "           ,[TenDem]\n"
                + "           ,[Ho]\n"
                + "           ,[Gioitinh]\n"
                + "           ,[NgaySinh]\n"
                + "           ,[Email]\n"
                + "           ,[Sdt]\n"
                + "           )\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        int check = 0;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setObject(1, kh.getTen());
            ps.setObject(2, kh.getTenDem());
            ps.setObject(3, kh.getHo());
            ps.setObject(4, kh.isGioiTinh());
            ps.setObject(5, kh.getNgaySinh());
            ps.setObject(6, kh.getEmail());
            ps.setObject(7, kh.getSdt());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean update(KhachHang kh, String Ten) {
        String query = "UPDATE [dbo].[KhachHang]\n"
                + "   SET [Ten] = ?\n"
                + "      ,[TenDem] = ?\n"
                + "      ,[Ho] = ?\n"
                + "      ,[Gioitinh] = ?\n"
                + "      ,[NgaySinh] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[Sdt] = ?\n"
                + " WHERE Ten=?";
        int check = 0;

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setObject(1, kh.getTen());
            ps.setObject(2, kh.getTenDem());
            ps.setObject(3, kh.getHo());
            ps.setObject(4, kh.isGioiTinh());
            ps.setObject(5, kh.getNgaySinh());
            ps.setObject(6, kh.getEmail());
            ps.setObject(7, kh.getSdt());
            ps.setObject(8, Ten);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<KhachHang> search(String sdt) {
        String query = "SELECT\n"
                + "      [Ten]\n"
                + "      ,[TenDem]\n"
                + "      ,[Ho]\n"
                + "      ,[Gioitinh]\n"
                + "      ,[NgaySinh]\n"
                + "      ,[Email]\n"
                + "      ,[Sdt]\n"
                + "      ,[Diemthuong]\n"
                + "  FROM [dbo].[KhachHang] where Sdt=?";
        List<KhachHang> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setObject(1, sdt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(7));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
