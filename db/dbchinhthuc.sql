Create Database DuAn1
go
Use DuAn1
go
--Màu Sắc
Create Table MauSac(
Id INT Identity(1,1) primary key,
Ten nvarchar(50) not  null
)
go
--Kích cỡ
Create Table KichCo(
Id INT Identity(1,1) primary key,
Ten nvarchar(50) not  null
)
go
--Chất liệu
Create Table ChatLieu(
Id INT Identity(1,1) primary key,
Ten nvarchar(50) not  null
)
go
--Danh Mục sản phẩm
Create Table DanhMucSP(
Id INT Identity(1,1) primary key,
Ten nvarchar(50) not null
)
go
--Thương hiệu
Create Table ThuongHieu(
Id INT Identity(1,1) primary key,
Ten nvarchar(50) not null
)
go
--Nhà sản xuất
Create Table NSX(
Id INT Identity(1,1) primary key,
Ten nvarchar(50) not null
)
go
--Chức vụ
Create Table ChucVu(
Id INT Identity(1,1) primary key,
Ten nvarchar(50) not null
)
go
--Khuyến mãi
Create Table KhuyenMai(
Id INT Identity(1,1) primary key,
Ten nvarchar(50) not null,
Ngaybatdau Date not null,
Ngayketthuc date not null,
HinhthucKM nvarchar(50) not null,
Giatrigiam decimal DEFAULT 0
)
go
--Chi tiết sản phẩm
Create Table ChitietSP(
Id INT Identity(1,1) PRIMARY KEY ,
Ma nvarchar(10) not  null,
Ten nvarchar(50) not null,
IdNsx INT foreign key references NSX(Id),
IdMauSac INT foreign key references MauSac(Id),
IdDMuc INT foreign key references DanhMucSP(Id),
IdKC INT foreign key references KichCo(Id),
IdCL INT foreign key references ChatLieu(Id),
IdTH INT foreign key references ThuongHieu(Id),
IdKM INT foreign key references KhuyenMai(Id),
MoTa NVARCHAR(50) DEFAULT NULL,
SoLuongTon INT,
GiaNhap DECIMAL(20,0) DEFAULT 0,
GiaBan DECIMAL(20,0) DEFAULT 0,
QrCode nvarchar(max) DEFAULT null
)
go
--User
Create Table Users(
Id INT Identity(1,1) PRIMARY KEY ,
Ten NVARCHAR(30) not null,
TenDem NVARCHAR(30) DEFAULT NULL,
Ho NVARCHAR(30) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
Gioitinh bit null,
Sdt VARCHAR(30) DEFAULT NULL,
IdCV INT foreign key references ChucVu(Id),
TaiKhoan VARCHAR(MAX) DEFAULT NULL,
MatKhau VARCHAR(MAX) DEFAULT NULL,
Email varchar(max) not null,
TrangThai bit DEFAULT 0
)
go
--Khách hàng
Create Table KhachHang(
Id INT Identity(1,1) PRIMARY KEY ,
Ten NVARCHAR(30) not null,
TenDem NVARCHAR(30) DEFAULT NULL,
Ho NVARCHAR(30) DEFAULT NULL,
Gioitinh bit Null,
NgaySinh DATE DEFAULT NULL,
Email varchar(max) not null,
Sdt VARCHAR(30) DEFAULT NULL,
Diemthuong int DEFAULT NULL
)
go
--Hóa đơn
Create Table HoaDon(
Id INT Identity(1,1) primary key ,
IdKH INT foreign key references KhachHang(Id),
IdNV INT foreign key references Users(Id),
Ma VARCHAR(20) UNIQUE,
NgayTao DATE DEFAULT NULL,
NgayThanhToan DATE DEFAULT NULL,
TinhTrang bit DEFAULT 0
)
go
--Hóa đơn chi tiết
Create Table HoaDonChiTiet(
IdHD int foreign key references HoaDon(Id) not null,
IdCTSP int foreign key references ChitietSP(Id) not null,
Soluong int not  null,
Dongia decimal not  null,
primary key(IdHD,IdCTSP),
Dongiakhigiam decimal not  null
)
ALTER TABLE Hoadon

ADD Ghichu varchar(255)
ALTER TABLE Khuyenmai

ADD Trangthai bit Default 0
ALTER TABLE HoaDonChiTiet

Drop COLUMN DonGiakhiGiam
ALTER TABLE Hoadon

ADD TongTien decimal
--chuc vu
GO
INSERT INTO ChucVu values(N'quản lý')
INSERT INTO ChucVu values(N'nhân viên')
--user
INSERT INTO USERS VALUES(N'Nam',N'Đức',N'Nguyễn','06-08-2003',0,'0967493115',1,'1','1','namndph22694@fpt.edu.vn',0)
INSERT INTO USERS VALUES(N'Hùng',N'Đức',N'Nguyễn','06-07-2003',0,'0967493115',2,'2','2','hungndph889977@fpt.edu.vn',0)
INSERT INTO USERS VALUES(N'Huy',N'Duy',N'Nguyễn','06-04-2002',0,'0967493135',2,'3','3','huydnph27822782@fpt.edu.vn',0)
INSERT INTO USERS VALUES(N'Thể',N'Văn',N'Nguyễn','06-02-2009',0,'0967233115',2,'4','4','thenvhahaha09022@fpt.edu.vn',0)
INSERT INTO USERS VALUES(N'Hiếu',N'Văn',N'Nguyễn','01-08-2003',0,'0945983115',2,'5','5','hieuhahahha09223@fpt.edu.vn',0)
--khach hang
INSERT INTO KhachHang VALUES(N'Thể',N'Văn',N'Nguyễn',0,'09-08-2001','hevnhhahaa@gmail.com','09786664432',100)
INSERT INTO KhachHang VALUES(N'Đức',N'Văn',N'Nguyễn',0,'09-08-2000','thenvhahaha09022@gmail.com','0967233115',200)
INSERT INTO KhachHang VALUES(N'Nam',N'Văn',N'Nguyễn',0,'09-08-2004','hieuhahahha09223@gmail.com','09786664432',300)
INSERT INTO KhachHang VALUES(N'Huy',N'Văn',N'Nguyễn',0,'09-08-2005','hungndph889977@gmail.com','0967233115',400)
INSERT INTO KhachHang VALUES(N'Hiếu',N'Văn',N'Nguyễn',0,'09-08-2006','hieuhahahha09223@gmail.com','09786664432',500)
--Hoa don
INSERT INTO HoaDon(IdKH,IdNV,Ma,NgayTao,NgayThanhToan,TinhTrang,Ghichu,TongTien) VALUES(1,1,'HD01','03/23/2023','03/23/2023',0,'ghi chu 1',1000000)
INSERT INTO HoaDon VALUES(2,1,'HD02','03/23/2023','03/23/2023',0,'ghi chu 1',1000000)
INSERT INTO HoaDon VALUES(3,1,'HD03','03/23/2023','03/23/2023',0,'ghi chu 1',1000000)



--mau
INSERT INTO MauSac VALUES(N'Ánh kim')
INSERT INTO MauSac VALUES(N'Đen nhám')
INSERT INTO MauSac VALUES(N'Xanh')
INSERT INTO MauSac VALUES(N'Vàng nhạt')
INSERT INTO MauSac VALUES(N'Ánh kim')
INSERT INTO MauSac VALUES(N'Dằn di')
INSERT INTO MauSac VALUES(N'Đỏ nhạt')
INSERT INTO MauSac VALUES(N'Tím mộng mơ')
--chat lieu
INSERT INTO ChatLieu VALUES(N'Da')
INSERT INTO ChatLieu VALUES(N'Da cao cấp')
INSERT INTO ChatLieu VALUES(N'Vải dệt kim')
INSERT INTO ChatLieu VALUES(N'polyester')
--thuong hieu
INSERT INTO ThuongHieu VALUES(N'Nike')
INSERT INTO ThuongHieu VALUES(N'Adidas')
INSERT INTO ThuongHieu VALUES(N'Puma')
INSERT INTO ThuongHieu VALUES(N'New Balance')
INSERT INTO ThuongHieu VALUES(N'Reebok')
INSERT INTO ThuongHieu VALUES(N'ASICS')
INSERT INTO ThuongHieu VALUES(N'Vans')
INSERT INTO ThuongHieu VALUES(N'Converse')
INSERT INTO ThuongHieu VALUES(N'Skechers')
INSERT INTO ThuongHieu VALUES(N'Under Armour')
--nsx
INSERT INTO NSX VALUES(N'Nike')
INSERT INTO NSX VALUES(N'Inc')
INSERT INTO NSX VALUES(N'Adidas AG')
INSERT INTO NSX VALUES(N'Puma SE')
INSERT INTO NSX VALUES(N'New Balance Athletics')
INSERT INTO NSX VALUES(N'Reebok')
INSERT INTO NSX VALUES(N'International ')
INSERT INTO NSX VALUES(N'ASICS Corporation')
INSERT INTO NSX VALUES(N'Vans')
INSERT INTO NSX VALUES(N'Converse ')
INSERT INTO NSX VALUES(N'Skechers ')
INSERT INTO NSX VALUES(N'Salomon ')
INSERT INTO NSX VALUES(N'Columbia Sportswear Company')
INSERT INTO NSX VALUES(N'Timberland LLC')
INSERT INTO NSX VALUES(N'Birkenstock GmbH & Co. KG')
INSERT INTO NSX VALUES(N'Deckers Outdoor Corporation')
INSERT INTO NSX VALUES(N'UGG')
INSERT INTO NSX VALUES(N'Merrell')

--danhmuc
INSERT INTO DanhMucSP VALUES(N'Giày chạy bộ (running shoes)')
INSERT INTO DanhMucSP VALUES(N'Giày tập gym (training shoes)')
INSERT INTO DanhMucSP VALUES(N'Giày bóng rổ (basketball shoes)')
INSERT INTO DanhMucSP VALUES(N'Giày đá banh (soccer cleats)')
INSERT INTO DanhMucSP VALUES(N'Giày đi bộ (walking shoes)')
INSERT INTO DanhMucSP VALUES(N'Giày leo núi (hiking shoes)')
INSERT INTO DanhMucSP VALUES(N'Giày thể thao đa năng (cross-training shoes)	')
INSERT INTO DanhMucSP VALUES(N'Giày thể thao đa năng (cross-training shoes)')
INSERT INTO DanhMucSP VALUES(N'Giày golf (golf shoes)')
INSERT INTO DanhMucSP VALUES(N'Giày skate (skate shoes)')
INSERT INTO DanhMucSP VALUES(N'Giày chơi cầu lông (badminton shoes)')
--kich co
INSERT INTO KichCo VALUES(N'34-35')
INSERT INTO KichCo VALUES(N'35')
INSERT INTO KichCo VALUES(N'35-36')
INSERT INTO KichCo VALUES(N'36')
INSERT INTO KichCo VALUES(N'36-37')
INSERT INTO KichCo VALUES(N'37')
INSERT INTO KichCo VALUES(N'37-38')
INSERT INTO KichCo VALUES(N'38')
INSERT INTO KichCo VALUES(N'38-39')
INSERT INTO KichCo VALUES(N'39')
INSERT INTO KichCo VALUES(N'39-40')
INSERT INTO KichCo VALUES(N'40')
INSERT INTO KichCo VALUES(N'40-41')
INSERT INTO KichCo VALUES(N'41')
INSERT INTO KichCo VALUES(N'41-42')
INSERT INTO KichCo VALUES(N'42-43')

--khuyen mai
INSERT INTO KhuyenMai VALUES(N'Black Friday','03-23-2023','03-30-2023','vnd',1000000,0)
INSERT INTO KhuyenMai VALUES(N'Valentine Lễ Tình Nhân','03-24-2023','03-25-2023','%',20,0)
INSERT INTO KhuyenMai VALUES(N'Giỗ Tổ Hùng Vương','03-24-2023','03-30-2023','vnd',200000,0)

--chi tiet
INSERT INTO ChitietSP VALUES('SP01','Nike Air Zoom Pegasus',1,1,1,1,1,1,1,'mota1',20,199000,300000,'qrcode1')
--hdct
INSERT INTO HoaDonChiTiet VALUES(1,1,10,300000)







