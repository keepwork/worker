<%-- 
    Document   : render-angular-and-cylinder-gauge
    Author     : sguha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Render Angular and Cylinder Gauge</title>
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
        <div id="chart1"></div>
        <div id="chart2"></div>
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
            FusionCharts angularGauge = new FusionCharts(
                        "AngularGauge",// chartType
                        "ex8",// chartId
                        "600","400",// chartWidth, chartHeight
                        "chart1",// chartContainer
                        "json",// dataFormat
                        // dataSource
                        "{\"chart\": {\"caption\": \"Customer Satisfaction Score\",\"lowerlimit\": \"0\",\"upperlimit\": \"100\",\"lowerlimitdisplay\": \"Bad\",\"upperlimitdisplay\": \"Good\",\"palette\": \"1\",\"numbersuffix\": \"%\",\"tickvaluedistance\": \"10\",\"showvalue\": \"0\",\"gaugeinnerradius\": \"0\",\"bgcolor\": \"FFFFFF\",\"pivotfillcolor\": \"333333\",\"pivotradius\": \"8\",\"pivotfillmix\": \"333333, 333333\",\"pivotfilltype\": \"radial\",\"pivotfillratio\": \"0,100\",\"showtickvalues\": \"1\",\"showborder\": \"0\"},\"colorrange\": {\"color\": [{\"minvalue\": \"0\",\"maxvalue\": \"45\",\"code\": \"e44a00\"},{\"minvalue\": \"45\",\"maxvalue\": \"75\",\"code\": \"f8bd19\"},{\"minvalue\": \"75\",\"maxvalue\": \"100\",\"code\": \"6baa01\"}]},\"dials\": {\"dial\": [{\"value\": \"92\",\"rearextension\": \"15\",\"radius\": \"100\",\"bgcolor\": \"333333\",\"bordercolor\": \"333333\",\"basewidth\": \"8\"}]}}"
                    );
            FusionCharts cylinder = new FusionCharts(
                        "cylinder",// chartType
                        "ex82",// chartId
                        "600", "400",// chartWidth, chartHeight
                        "chart2",// chartContainer
                        "json",// dataFormat
                        // dataSource
                        "{\"chart\": {\"manageresize\": \"1\",\"bgcolor\": \"FFFFFF\",\"bgalpha\": \"0\",\"showborder\": \"0\",\"lowerlimit\": \"0\",\"upperlimit\": \"100\",\"showtickmarks\": \"0\",\"showtickvalues\": \"0\",\"showlimits\": \"0\",\"numbersuffix\": \"%\",\"decmials\": \"0\",\"cylfillcolor\": \"CC0000\",\"basefontcolor\": \"CC0000\",\"chartleftmargin\": \"15\",\"chartrightmargin\": \"15\",\"charttopmargin\": \"15\"},\"value\": \"44\",\"annotations\": {\"groups\": [{\"showbelow\": \"1\",\"items\": [{\"type\": \"rectangle\",\"x\": \"$chartStartX+1\",\"y\": \"$chartStartY+1\",\"tox\": \"$chartEndX-1\",\"toy\": \"$chartEndY-1\",\"color\": \"FFFFFF\",\"alpha\": \"100\",\"showborder\": \"0\",\"bordercolor\": \"CC0000\",\"borderthickness\": \"2\",\"radius\": \"10\"}]}]}}"
                    );
        %>
<!--    Step 5: Render the chart    -->            
        <%=
                angularGauge.render()
        %>
        <%=
                cylinder.render()
        %>
        
        
    </body>
</html>
