package com.thinking.machines.hr.dl.dao;
import java.io.*;
import java.text.*;
import java.util.*;
import java.math.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
public class EmployeeDAO implements EmployeeDAOInterface
{
private static final String FILE_NAME="employee.data";
public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
if(employeeDTO==null) throw new DAOException("Employee is null");
String employeeId;
String name=employeeDTO.getName();
if(name==null) throw new DAOException("Employee name is null");
name=name.trim();
if(name.length()==0) throw new DAOException("Length of name is zero");
int designationCode=employeeDTO.getDesignationCode();
if(designationCode<=0) throw new DAOException("Invalid designation code"+designationCode);
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
boolean isDesignationCodeValid=designationDAO.codeExists(designationCode);
if(isDesignationCodeValid==false) throw new DAOException("Invalid Designation code: "+designationCode);
Date dateOfBirth=employeeDTO.getDateOfBirth();
char gender=employeeDTO.getGender();
boolean isIndian=employeeDTO.getIsIndian();
BigDecimal basicSalary=employeeDTO.getBasicSalary();
if(basicSalary==null) throw new DAOException("Basic salary is null");
if(basicSalary.signum()==-1) throw new DAOException("Basic salary is negative");
String panNumber=employeeDTO.getPANNumber();
if(panNumber==null) throw new DAOException("PAN Number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("Length of PAN Number is zero");
String aadharCardNumber=employeeDTO.getAadharCardNumber();
if(aadharCardNumber==null) throw new DAOException("Aadhar card number is null");
if(aadharCardNumber.length()==0) throw new DAOException("Length of Aadhar Card number is zero");
try
{
int lastGeneratedEmployeeId=10000000;
String lastGeneratedEmployeeIdString="";
int recordCount=0;
String recordCountString="";
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
lastGeneratedEmployeeIdString=String.format("%-10s","10000000");
randomAccessFile.writeBytes(lastGeneratedEmployeeIdString+"\n");
recordCountString=String.format("%-10s","0");
randomAccessFile.writeBytes(recordCountString+"\n");
}
else
{
lastGeneratedEmployeeId=Integer.parseInt(randomAccessFile.readLine().trim());
recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
}
boolean panNumberExists,aadharCardNumberExists;
panNumberExists=false;
aadharCardNumberExists=false;
String fPANNumber;
String fAadharCardNumber;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(x=1;x<=7;x++) randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(panNumberExists==false && fPANNumber.equalsIgnoreCase(panNumber))
{
panNumberExists=true;
}
if(aadharCardNumberExists==false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
aadharCardNumberExists=true;
}
if(panNumberExists && aadharCardNumberExists) break;
}
if(panNumberExists && aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("PAN number ("+panNumber+") and Aadhar card number ("+aadharCardNumber+") exists");
if(panNumberExists)
{
randomAccessFile.close();
throw new DAOException("PAN number ("+panNumber+") exists");
}
if(aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number ("+aadharCardNumber+") exists");
}
lastGeneratedEmployeeId++;
employeeId="A"+String.format("%-10d",lastGeneratedEmployeeId);
recordCount++;
randomAccessFile.writeBytes(employeeId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(designationCode+"\n");
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth)+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(basicSalary.toPlainString()+"\n");
randomAccessFile.writeBytes(panNumber+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");
randomAccessFile.seek(0);
lastGeneratedEmployeeIdString=String.format("%-10d",lastGeneratedEmployeeId);
recordCountString=String.format("%-10d",recordCount);
randomAccessFile.writeBytes(lastGeneratedEmployeeIdString+"\n");
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.close();
employeeDTO.setEmployeeId(employeeId);
}
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
throw new DAOException("Not Yet Implemented");
}
public void delete(String employeeId) throws DAOException
{
throw new DAOException("Not Yet Implemented");
}
public Set<EmployeeDTOInterface> getAll() throws DAOException
{
throw new DAOException("Not Yet Implemented");
}
public Set<EmployeeDTOInterface> getByDesignationCode(int designationCode) throws DAOException
{
throw new DAOException("Not Yet Implemented");
}
public boolean isDesignationAlloted(int designationCode) throws DAOException
{
throw new DAOException("Not Yet Implemented");
}
public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
throw new DAOException("Not Yet Implemented");
}
public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
throw new DAOException("Not Yet Implemented");
}
public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
throw new DAOException("Not Yet Implemented");
}
public int getCount() throws DAOException
{
throw new DAOException("Not Yet Implemented");
}
public int getCOuntByDesignation(int designationCode) throws DAOException
{
throw new DAOException("Not Yet Implemented");
}
}