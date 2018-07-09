<%-- 
    Document   : client-side-chart-export
    Author     : sguha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Side Chart Export</title>
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
                        "ex7",// chartId
                        "600", "400",// chartWidth, chartHeight
                        "chart",// chartContainer
                        "json",// dataFormat
                        // dataSource
                        "{  \"chart\":	  {	 \"caption\":\"Harry\'s SuperMart\",		 \"subCaption\":\"Top 5 stores in last month by revenue\",		 \"numberPrefix\":\"$\",		 \"exportEnabled \":\"1\",		 \"theme\":\"ocean\"	  },	  \"data\":	  [	 {		\"label\":\"Bakersfield Central\",			\"value\":\"880000\"		 },		 {		\"label\":\"Garden Groove harbour\",			\"value\":\"730000\"		 },		 {		\"label\":\"Los Angeles Topanga\",			\"value\":\"590000\"		 },		 {		\"label\":\"Compton-Rancho Dom\",			\"value\":\"520000\"		 },		 {		\"label\":\"Daly City Serramonte\",			\"value\":\"330000\"		 }	  ]		}"
                    );
        %>
<!--    Step 5: Render the chart    -->          
        <%=lineChart.render()%>
    </body>
</html>
