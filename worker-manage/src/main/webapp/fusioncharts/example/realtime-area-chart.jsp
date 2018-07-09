<%-- 
    Document   : realtime-area-chart
    Author     : sguha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Realtime Area Chart</title>
<!-- Step 1: Include the `fusioncharts.js` file. This file is needed to
        render the chart. Ensure that the path to this JS file is correct.
        Otherwise, it may lead to JavaScript errors.
-->        
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
<!--    Step 2: Include the `FusionCharts.java` file as a package in your 
        project.
-->         
        <%@page import="com.common.FusionCharts" %>
        
<!--    Step 3:Include the package in the file where you want to show 
        FusionCharts as follows.
        
        Step 4: Create a chart object using the FusionCharts JAVA class 
        constructor. Syntax for the constructor: 
        `FusionCharts("type of chart", "unique chart id", "width of chart",
                        "height of chart", "div id to render the chart", 
                        "data format", "data source")`   
-->         
        <% 
            
//            FusionCharts realtimearea = new FusionCharts(
//                        "realtimearea",// chartType
//                        "stockRealTimeChart",// chartId
//                        "100%","400",// chartWidth, chartHeight
//                        "chart",// chartContainer
//                        "json",// dataFormat
//                        // dataSource
//                        "{\"chart\": {\"caption\": \"Real-time stock price monitor\",\"subCaption\": \"Harry\'s SuperMart\",\"xAxisName\": \"Time\",\"yAxisName\": \"Stock Price\",\"numberPrefix\": \"$\",\"refreshinterval\": \"5\",\"yaxisminvalue\": \"35\",\"yaxismaxvalue\": \"36\",\"numdisplaysets\": \"10\",\"labeldisplay\": \"rotate\",\"showValues\": \"0\",\"showRealTimeValue\": \"0\", \"paletteColors\" : \"#0075c2,#1aaf5d\",\"baseFontColor\" : \"#333333\",\"baseFont\" : \"Helvetica Neue,Arial\",\"captionFontSize\" : \"14\",\"subcaptionFontSize\" : \"14\",\"subcaptionFontBold\" : \"0\",\"showBorder\" : \"0\",\"bgColor\" : \"#ffffff\",\"showShadow\" : \"0\",\"canvasBgColor\" : \"#ffffff\",\"canvasBorderAlpha\" : \"0\",\"divlineAlpha\" : \"100\",\"divlineColor\" : \"#999999\",\"divlineThickness\" : \"1\",\"divLineIsDashed\" : \"1\",\"divLineDashLen\" : \"1\",\"divLineGapLen\" : \"1\",\"usePlotGradientColor\" : \"0\",\"showplotborder\" : \"0\",\"showXAxisLine\" : \"1\",\"xAxisLineThickness\" : \"1\",\"xAxisLineColor\" : \"#999999\",\"showAlternateHGridColor\" : \"0\"},\"categories\": [{\"category\": [{ \"label\": \"Day Start\" }]}],\"dataset\": [{\"data\": [{ \"value\": \"35.27\" }]}]}",//datasource
//                        // extras (events atrribute)
//                        "{\"events\": {			\"initialized\": function (e) {				function addLeadingZero(num){					return (num <= 9)? (\"0\"+num) : num;				}				function updateData() {					var chartRef = FusionCharts(\"stockRealTimeChart\"),						currDate = new Date(),						label = addLeadingZero(currDate.getHours()) + \":\" +						addLeadingZero(currDate.getMinutes()) + \":\" +						addLeadingZero(currDate.getSeconds()),												randomValue = Math.floor(Math.random()     												 * 50) / 100 + 35.25,												strData = \"&label=\" + label + \"&value=\" + randomValue;										chartRef.feedData(strData);				}								var myVar = setInterval(function () {					updateData();				}, 5000);			}		}}"
//                    );
        %>
<!--    Step 5: Render the chart    -->
<%--   <%=realtimearea.render()%> --%>
</body>
</html>
