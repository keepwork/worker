<%-- 
    Document   : render-pyramid-and-funnel-charts
    Author     : sguha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Render Pyramid and Funnel Charts</title>
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
            FusionCharts pyramid = new FusionCharts(
                        "pyramid",// chartType
                        "ex9",// chartId
                        "600", "400",// chartWidth, chartHeight
                        "chart1",// chartContainer
                        "json",// dataFormat
                        // dataSource
                        "{\"chart\": {\"bgcolor\": \"FFFFFF\",\"caption\": \"Revenue distribution for 2013\",\"basefontcolor\": \"333333\",\"decimals\": \"0\",\"numbersuffix\": \"M\",\"numberprefix\": \"$\",\"pyramidyscale\": \"40\",\"chartbottommargin\": \"0\",\"captionpadding\": \"0\",\"showborder\": \"0\"},\"data\": [{\"value\": \"17\",\"name\": \"Products\",\"color\": \"008ee4\"},{\"value\": \"21\",\"name\": \"Services\",\"color\": \"6baa01\"},{\"value\": \"20\",\"name\": \"Consultancy\",\"color\": \"f8bd19\"},{\"value\": \"5\",\"name\": \"Others\",\"color\": \"e44a00\"}]}"
                    );
            FusionCharts funnel = new FusionCharts(
                        "funnel",// chartType
                        "ex92",// chartId
                        "600", "400",// chartWidth, chartHeight
                        "chart2",// chartContainer
                        "json",// dataFormat
                        // dataSource
                        "{\"chart\": {\"bgcolor\": \"FFFFFF\",\"caption\": \"Conversion Funnel - 2013\",\"decimals\": \"1\",\"basefontsize\": \"11\",\"issliced\": \"0\",\"ishollow\": \"1\",\"labeldistance\": \"8\",\"showBorder\": \"0\"},\"data\": [{\"label\": \"Website Visits\",\"value\": \"385634\"},{\"label\": \"Downloads\",\"value\": \"145631\",\"color\": \"008ee4\"},{\"label\": \"Interested to buy\",\"value\": \"84564\",\"color\": \"f8bd19\"},{\"label\": \"Contract finalized\",\"value\": \"50654\",\"color\": \"6baa01\"},{\"label\": \"Purchased\",\"value\": \"25342\",\"color\": \"e44a00\"}]}"
                    );
        %>
<!--    Step 5: Render the chart    -->  
        <%=
                pyramid.render()
        %>
        <%=
                funnel.render()
        %>
    </body>
</html>
