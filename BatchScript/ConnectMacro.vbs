Dim args,objExcel


set args = wscript.Arguments
set objExcel = Createobject("Excel.Application")

objExcel.Workbooks.Open("C:\Users\IBM_ADMIN\Desktop\Workbench\ILC Macro.xlsm")
objExcel.Visible  =  True

objExcel.Run  "'ILC Macro.xlsm'!ThisWorkbook.Copy_Macro_ILC"

objExcel.Activeworkbook.Save
objExcel.Activeworkbook.Close(0)
objExcel.Quit

