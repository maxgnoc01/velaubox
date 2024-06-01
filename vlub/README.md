1. Cấu hình đề nghị:
Máy đã có jdk 17 trở lên
Đã cài MySQL
IDE (recommend Eclipse,...)
Postman
2. Các bước để chạy project
	- Chạy các câu lệnh khởi tạo DB trong thư mục \shoppingCart\database
	- Sửa file \shoppingCart\src\main\resources tương ứng với cấu hình database (port, tên schema, ... Nếu thay đổi tên schema thì cần update các file trong \shoppingCart\src\main\java\com\shoppingCart\app\model (phần catalog))
	- Import project vào IDE để update thư viện
	- Chạy câu lệnh trong terminal: 
		+ mvn clean install -DskipTest
		+ mvn spring-boot: run (hoặc có thể chạy bằng IDE)
	- Sử dụng Post Man để call các API tương ứng đề bài. File Postman config tham khảo được để cùng thư mục chứa file này.