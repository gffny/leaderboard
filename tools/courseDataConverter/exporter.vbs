Option Explicit 

'Define the variables!
Dim objXLApp, objXLWb, objXLWs
Dim clubName
Dim courseName
Dim teeColour
Dim slopeIndex
Dim nameArray(18)
Dim yrdsArray(18)
Dim indexArray(18)
Dim parArray(18)
Dim count

Dim JSON

'~~> Working with Excel .. Sheet1
Set objXLApp = CreateObject("Excel.Application")
Set objXLWb = objXLApp.Workbooks.Open("C:\dev\leaderboard\tools\courseDataConverter\LeaderboardCourseExporter.xlsx")
Set objXLWs = objXLWb.Sheets(1)

'Read the data from the excel sheet
clubName = objXLWs.Cells(1, 2).Value
courseName =  objXLWs.Cells(3, 2).Value
teeColour = objXLWs.Cells(5,2).Value
slopeIndex = objXLWs.Cells(7,2).Value
For count = 0 to 17
    nameArray(count) = objXLWs.Cells(count+10,2).Value
	yrdsArray(count) = objXLWs.Cells(count+10,4).Value
	parArray(count) = objXLWs.Cells(count+10,5).Value
	indexArray(count) = objXLWs.Cells(count+10,6).Value
Next

'Spit it out in the format 
'{"name" : "The Old Course",	"description" : "....",	"slope" : "155", "red" : { "1" : { "distance" : "255", "par" : "4",	"index" : "13",	"description" : "...." },"2" : { ... }	} }


JSON = "{ 'name' : '" & courseName & "', 'description' : '" & clubName & "', 'slope' : '" & slopeIndex & "', '" & teeColour & "' : { "
For count = 10 to 17
	JSON = JSON & "'" & count+1 & "' : { 'distance' : '" & yrdsArray(count) & "', 'par' : '" & parArray(count) & "', 'index' : '" & indexArray(count) & "' }"
	If count = 17 Then
		JSON = JSON & " }"
	Else
	JSON = JSON & ", "
	End If
Next

JSON = JSON & " }"


'Write the formated string to a file
Dim objTextFile, fileName
fileName="result.txt"
Set objTextFile = CreateObject("Scripting.FileSystemObject").OpenTextFile(fileName, 2, true)
objTextFile.Write(JSON)
objTextFile.Close
