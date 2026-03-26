package com.college.service.login;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.college.entity.login.Attendance;
import com.college.repository.myprofile.AttendanceRepository;
import com.college.service.notify.NotificationService;

@Service
public class AttendanceService {

	@Autowired
    private AttendanceRepository repo;
	
	@Autowired
    private NotificationService notificationService;

	public List<Attendance> getAttendanceByPrn(Long prn) {

        return repo.findByPrn(prn);

    }
    // Get all attendance of student
    public List<Attendance> getStudentAttendance(Long prn) {
        return repo.findByPrn(prn);
    }

    // Month wise
    public List<Attendance> getStudentAttendanceByMonth(Long prn, String month) {
        return repo.findByPrnAndMonth(prn, month);
    }

    // Month + Year wise
    public List<Attendance> getStudentAttendanceByMonthAndYear(Long prn, String month, String year) {
        return repo.findByPrnAndMonthAndYear(prn, month, year);
    }
    
    public List<Attendance> getAttendanceByMonthYear(Long prn, String month, String year) {
        return repo.findByPrnAndMonthAndYear(prn, month, year);
    }

    // Excel Upload
    public void uploadExcel(MultipartFile file, String month, String year) {

        try {

            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            DataFormatter formatter = new DataFormatter();

            for (Row row : sheet) {

                if (row.getRowNum() == 0) continue;

                String prnStr = formatter.formatCellValue(row.getCell(0));

                if (prnStr == null || prnStr.trim().isEmpty()) {
                    continue;
                }

                Attendance a = new Attendance();

                Long prn = Long.parseLong(prnStr);

                a.setPrn(prn);

                String subject = formatter.formatCellValue(row.getCell(1));
                a.setSubject(subject);

                String totalStr = formatter.formatCellValue(row.getCell(2));
                int total = totalStr.isEmpty() ? 0 : Integer.parseInt(totalStr);

                String attendedStr = formatter.formatCellValue(row.getCell(3));
                int attended = attendedStr.isEmpty() ? 0 : Integer.parseInt(attendedStr);

                a.setTotalClasses(total);
                a.setAttendedClasses(attended);

                double percentage = total == 0 ? 0 : (attended * 100.0) / total;

                a.setPercentage(percentage);

                a.setMonth(month);
                a.setYear(year);

                repo.save(a);

//                // 🔔 SEND NOTIFICATION TO STUDENT
//                notificationService.sendToStudent(
//                        prn,
//                        "Attendance uploaded for " + subject +
//                        " (" + month + " - " + year + ")"
//                );
                
            }

            workbook.close();

            // 🔔 BROADCAST OPTIONAL
            notificationService.sendToAll(
                    "New attendance has been uploaded for " + month + " - " + year
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}