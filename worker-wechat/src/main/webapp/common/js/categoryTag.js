
//辖区选择
var optionArray = new Array();
function onChange_(_select, child, idName) {
    var childSelect = "";
    if(child != "" && child != null && document.getElementById(child) != null ){
      childSelect = document.getElementById(child);
      while (childSelect.length > 1) {
		childSelect.removeChild(childSelect.options[childSelect.length - 1]);
	  }
	  if( child == idName + "_secondLevel" ){
        var three_level = document.getElementById(idName + "_threeLevel");
        while (three_level!=null && three_level.length > 1) {
		  three_level.removeChild(three_level.options[three_level.length - 1]);
	    }
	  }
    }  
	var k = 1;
	var i = 0;
	while (i < optionArray.length) {
		if (optionArray[i][0] == _select.value) {
			var option = new Option(optionArray[i][2], optionArray[i][1]);
			childSelect.options[k] = option;
			k = k + 1;
		}
		i = i + 1;
	} 
    if( document.getElementById(idName + "_threeLevel") != null && document.getElementById(idName + "_threeLevel").value != "" ){
        document.getElementById(idName).value = document.getElementById(idName + "_threeLevel").value;
    }else if( document.getElementById(idName + "_secondLevel") != null && document.getElementById(idName + "_secondLevel").value != "" ){
        document.getElementById(idName).value = document.getElementById(idName + "_secondLevel").value;
    }else if( document.getElementById(idName + "_fistLevel") != null && document.getElementById(idName + "_fistLevel").value != "" ){
        document.getElementById(idName).value = document.getElementById(idName + "_fistLevel").value;
    }else{
        document.getElementById(idName).value = '';
    }    	
	//alert( document.getElementById(idName).value = document.getElementById(idName + "_secondLevel").value);	
}     

	
	