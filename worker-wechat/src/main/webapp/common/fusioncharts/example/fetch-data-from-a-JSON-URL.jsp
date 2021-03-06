<%-- 
    Document   : fetch-data-from-a-JSON-URL
    Author     : sguha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fetch data from a JSON URL</title>
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
        
            FusionCharts lineChart = new FusionCharts(
                        "column2d",// chartType
                        "ex5",// chartId
                        "600", "400",// chartWidth, chartHeight
                        "chart",// chartContainer
                        "jsonurl",// dataFormat
                        "../data/data.json"
                    );
        %>
<!--    Step 5: Render the chart    -->          
        <%=lineChart.render()%>
    </body>
</html>
