
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Introduction JSP </title>
<!-- Step 1: Include the `fusioncharts.js` file. This file is needed to
        render the chart. Ensure that the path to this JS file is correct.
        Otherwise, it may lead to JavaScript errors.
-->        
        <script src="scripts/fusioncharts.js"></script>
    <body>

        <div id="chart"></div>
        
<!--    Step 2: Include the `FusionCharts.java` file as a package in your 
        project.
-->
        <%@page import="com.common.FusionCharts" %>
    
        <%
            FusionCharts columnChart= new FusionCharts(
            "column2d",// chartType
                        "chart1",// chartId
                        "600","400",// chartWidth, chartHeight
                        "chart",// chartContainer
                        "json",// dataFormat
                        "{   \"chart\": {  \"caption\": \"主标题\", \"subCaption\": \"副标题\",\"xAxisName\": \"Month\",\"yAxisName\": \"Revenues (In USD)\",                   \"numberPrefix\": \"$\",                   \"paletteColors\": \"#0075c2\",                   \"bgColor\": \"#ffffff\",                   \"borderAlpha\": \"20\",                   \"canvasBorderAlpha\": \"0\",                   \"usePlotGradientColor\": \"0\",                   \"plotBorderAlpha\": \"10\",                   \"placeValuesInside\": \"1\",                   \"rotatevalues\": \"1\",                   \"valueFontColor\": \"#ffffff\",                   \"showXAxisLine\": \"1\",                   \"xAxisLineColor\": \"#999999\",                   \"divlineColor\": \"#999999\",                   \"divLineIsDashed\": \"1\",                   \"showAlternateHGridColor\": \"0\",                   \"subcaptionFontSize\": \"14\",                   \"subcaptionFontBold\": \"0\"               },               \"data\": [{                   \"label\": \"Jan\",                   \"value\": \"420000\"               }, {                   \"label\": \"Feb\",                   \"value\": \"810000\"               }, {                   \"label\": \"Mar\",                   \"value\": \"720000\"               }, {                   \"label\": \"Apr\",                   \"value\": \"550000\"               }, {                   \"label\": \"May\",                   \"value\": \"910000\"               }, {                   \"label\": \"Jun\",                   \"value\": \"510000\"               }, {                   \"label\": \"Jul\",                   \"value\": \"680000\"               }, {                   \"label\": \"Aug\",                   \"value\": \"620000\"               }, {                   \"label\": \"Sep\",                   \"value\": \"610000\"               }, {                   \"label\": \"Oct\",                   \"value\": \"490000\"               }, {                   \"label\": \"Nov\",                   \"value\": \"900000\"               }, {                   \"label\": \"Dec\",                   \"value\": \"730000\"               }]           }"
                    );
           
            %>
<!--    Step 5: Render the chart    -->            
            <%=columnChart.render()%>
        
        
        <br>
        
        <table>
        	<tr><td><a href="staticExample/staticJsonString.jsp" target="_blank">staticJsonString.jsp</a></td></tr>
        	<tr><td><a href="staticExample/staticXmlString.jsp" target="_blank">staticXmlString.jsp</a></td></tr>
        	<tr><td>&nbsp;</td></tr>
        	<tr><td><a href="arrayExample/JsonExample.jsp" target="_blank">JsonExample.jsp 馅饼图2D</a></td></tr>
        	<tr><td><a href="arrayExample/XmlExample.jsp" target="_blank">XmlExample.jsp 横向柱型图2D</a></td></tr>
        	<tr><td><a href="databaseExample/basic-example.jsp" target="_blank">basic-example.jsp 竖向柱型图2D</a></td></tr>
        	<tr><td><a href="drillDownExample/country.jsp" target="_blank">country.jsp 竖向柱型图2D 可点击</a></td></tr>
        	<tr><td>&nbsp;</td></tr>
        	<tr><td><a href="new-samples/annotation.jsp" target="_blank">annotation.jsp 自定义图片</a></td></tr>
        	<tr><td><a href="new-samples/gantt-chart.jsp" target="_blank">gantt-chart.jsp 甘特图</a></td></tr>
        	<tr><td><a href="new-samples/gauge.jsp" target="_blank">gauge.jsp 仪表盘</a></td></tr>
        	<tr><td><a href="new-samples/linked-chart-single-level.jsp" target="_blank">linked-chart-single-level.jsp 简单一级柱型图</a></td></tr>
        	<tr><td><a href="new-samples/multi-seriese-chart.jsp" target="_blank">multi-seriese-chart.jsp 竖向多级柱型图</a></td></tr>
        	<tr><td><a href="new-samples/scatter-chart.jsp" target="_blank">scatter-chart.jsp 散点图</a></td></tr>
        	<tr><td><a href="new-samples/single-series-chart.jsp" target="_blank">single-series-chart.jsp 简单一级柱型图</a></td></tr>
        	<tr><td>&nbsp;</td></tr>
        	<tr><td><a href="example/a-3D-pie-chart.jsp" target="_blank">3D馅饼型</a></td></tr>
        	<tr><td><a href="example/a-column-line-and-area-combi-chart.jsp" target="_blank">a-column-line-and-area-combi-chart.jsp 柱线和区域组合图</a></td></tr>
        	<tr><td><a href="example/a-simple-chart.jsp" target="_blank">a-simple-chart.jsp</a></td></tr>
        	<tr><td><a href="example/client-side-chart-export.jsp" target="_blank">client-side-chart-export带导出功能</a></td></tr>
        	<tr><td><a href="example/fetch-data-from-a-JSON-URL.jsp" target="_blank">fetch-data-from-a-JSON-URL.jsp</a></td></tr>
        	<tr><td><a href="example/fetch-data-from-a-XML-URL.jsp" target="_blank">fetch-data-from-a-XML-URL.jsp</a></td></tr>
        	<tr><td><a href="example/loading-data-from-MySQL-database.jsp" target="_blank">loading-data-from-MySQL-database.jsp</a></td></tr>
        	<tr><td><a href="example/realtime-area-chart.jsp" target="_blank">realtime-area-chart.jsp</a></td></tr>
        	<tr><td><a href="example/render-angular-and-cylinder-gauge.jsp" target="_blank">缸型-仪表盘型</a></td></tr>
        	<tr><td><a href="example/render-pyramid-and-funnel-charts.jsp" target="_blank">金字塔型-碗型</a></td></tr>
        	<tr><td>&nbsp;</td></tr>
        	<tr><td><a href="new-samples/fusionmaps.jsp" target="_blank">fusionmaps.jsp 中国地图</a></td></tr>
        	<tr><td><a href="example/firstMap.jsp" target="_blank">中国地图</a></td></tr>
        	<tr><td>&nbsp;</td></tr>
        	
        </table>
    </body>
</html>
