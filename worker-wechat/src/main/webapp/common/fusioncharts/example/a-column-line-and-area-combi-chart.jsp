<%-- 
    Document   : a-column-line-and-area-combi-chart
    Author     : sguha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>A Column, Line and Area Combi Chart</title>
		<!--
		<script type="text/javascript" src="http://static.fusioncharts.com/code/latest/fusioncharts.js"></script>
        <script type="text/javascript" src="http://static.fusioncharts.com/code/latest/fusioncharts.charts.js"></script>
        <script type="text/javascript" src="http://static.fusioncharts.com/code/latest/themes/fusioncharts.theme.ocean.js"></script>
        -->
        <script type="text/javascript" src="../scripts/fusioncharts.js"></script>
        <script type="text/javascript" src="../scripts/fusioncharts.charts.js"></script>
        <script type="text/javascript" src="../scripts/themes/fusioncharts.theme.ocean.js"></script>
    </head>
    <body>
        <div id="chart"></div>
        <%
            /* **Step 1:** Include the `FusionCharts.java` file as a package in your project. */
            /* **Step 2:** Include the package in the file where you want to show FusionCharts as follows */    
        %>
        <%@page import="com.common.FusionCharts" %>
        <% 
            /* **Step 3:** Create a chart object using the FusionCharts JAVA class constructor. Syntax for the constructor: `FusionCharts("type of chart", "unique chart id", "width of chart", "height of chart", "div id to render the chart", "data format", "data source")`   */
            FusionCharts lineChart = new FusionCharts(
                        "mscombi2d",// chartType
                        "ex3",// chartId
                        "600","400",// chartWidth, chartHeight
                        "chart",// chartContainer
                        "json",// dataFormat
                        "{\"chart\": {\"caption\": \"Actual Revenues, Targeted Revenues & Profits\",\"subcaption\": \"Last year\",\"xaxisname\": \"Month\",\"yaxisname\": \"Amount (In USD)\",\"numberprefix\": \"$\",\"theme\": \"ocean\"			},			\"categories\": 			[{\"category\": [{\"label\": \"Jan\"					},					{\"label\": \"Feb\"					},					{\"label\": \"Mar\"					},					{\"label\": \"Apr\"					},					{\"label\": \"May\"					},					{\"label\": \"Jun\"					},					{\"label\": \"Jul\"					},					{\"label\": \"Aug\"					},					{\"label\": \"Sep\"					},					{\"label\": \"Oct\"					},					{\"label\": \"Nov\"					},					{\"label\": \"Dec\"					}				]			}],			\"dataset\": 			[{				\"seriesname\": \"Actual Revenue\",				\"data\": 				[					{\"value\": \"16000\"					},					{\"value\": \"20000\"					},					{\"value\": \"18000\"					},					{\"value\": \"19000\"					},					{\"value\": \"15000\"					},					{\"value\": \"21000\"					},					{\"value\": \"16000\"					},					{\"value\": \"20000\"					},					{\"value\": \"17000\"					},					{\"value\": \"25000\"					},					{\"value\": \"19000\"					},					{\"value\": \"23000\"					}				]			 },			 {				\"seriesname\": \"Projected Revenue\",				\"renderas\": \"line\",				\"showvalues\": \"0\",				\"data\": 				[					{\"value\": \"15000\"					},					{\"value\": \"16000\"					},					{\"value\": \"17000\"					},					{\"value\": \"18000\"					},					{\"value\": \"19000\"					},					{\"value\": \"19000\"					},					{\"value\": \"19000\"					},					{\"value\": \"19000\"					},					{\"value\": \"20000\"					},					{\"value\": \"21000\"					},					{\"value\": \"22000\"					},					{\"value\": \"23000\"					}				]			 },			 {				\"seriesname\": \"Profit\",				\"renderas\": \"area\",				\"showvalues\": \"0\",				\"data\": 				[					{\"value\": \"4000\"					},					{\"value\": \"5000\"					},					{\"value\": \"3000\"					},					{\"value\": \"4000\"					},					{\"value\": \"1000\"					},					{\"value\": \"7000\"					},					{\"value\": \"1000\"					},					{\"value\": \"4000\"					},					{\"value\": \"1000\"					},					{\"value\": \"8000\"					},					{\"value\": \"2000\"					},					{\"value\": \"7000\"					}				]			}]	}"
                    );
        %>
        <%
            /* **Step 4:** Render the chart */
        %>
        <%=lineChart.render()%>
    </body>
</html>
