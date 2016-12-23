        var xmlHttp3;
        var completeDiv3;
        var inputField3;
        var nameTable3;
        var nameTableBody3;

        function createXMLHttpRequest3() {
            if (window.ActiveXObject) {
                xmlHttp3 = new ActiveXObject("Microsoft.XMLHTTP");
            }
            else if (window.XMLHttpRequest) {
                xmlHttp3 = new XMLHttpRequest();                
            }
        }

        function initVars3() {
            inputField3 = document.getElementById("names3");        
            nameTable3 = document.getElementById("name_table3");
            completeDiv3 = document.getElementById("popup3");
            nameTableBody3 = document.getElementById("name_table_body3");
        }

        function findNames3() {
            initVars3();
            if (inputField3.value.length > 0) {
                createXMLHttpRequest3();            
                var url3 = "suggest.do?method=suggestCardreader&name=" +  inputField3.value;                        
                xmlHttp3.open("GET", url3, true);
                xmlHttp3.onreadystatechange = callback3;
                xmlHttp3.send(null);
            } else {
                clearNames3();
            }
        }

        function callback3() {
            if (xmlHttp3.readyState == 4) {
                if (xmlHttp3.status == 200) {
                   setNames3(xmlHttp3.responseXML.getElementsByTagName("name3"));
                } else if (xmlHttp3.status == 204){
                    clearNames3();
                }
            }
        }
        
        function setNames3(the_names) {            
            clearNames3();
            var size3 = the_names.length;
            setOffsets3();

            var row3, cell3, txtNode3;
            for (var i = 0; i < size3; i++) {
                var nextNode3 = the_names[i].firstChild.data;
                row3 = document.createElement("tr");
                cell3 = document.createElement("td");
                
                cell3.onmouseout = function() {this.className='mouseOver';};
                cell3.onmouseover = function() {this.className='mouseOut';};
                cell3.setAttribute("bgcolor", "#FFFAFA");
                cell3.setAttribute("border", "0");
                cell3.onclick = function() { populateName3(this); } ;                             

                txtNode3 = document.createTextNode(nextNode3);
                cell3.appendChild(txtNode3);
                row3.appendChild(cell3);
                nameTableBody3.appendChild(row3);
            }
        }

        function setOffsets3() {
            var end3 = inputField3.offsetWidth;
            var left3 = calculateOffsetLeft3(inputField3);
            var top3 = calculateOffsetTop3(inputField3) + inputField3.offsetHeight;

            completeDiv3.style.border = "black 1px solid";
            completeDiv3.style.left = left3 + "px";
            completeDiv3.style.top = top3 + "px";
            nameTable3.style.width = end3 + "px";
        }
        
        function calculateOffsetLeft3(field3) {
          return calculateOffset3(field3, "offsetLeft");
        }

        function calculateOffsetTop3(field3) {
          return calculateOffset3(field3, "offsetTop");
        }

        function calculateOffset3(field3, attr3) {
          var offset3 = 0;
          while(field3) {
            offset3 += field3[attr3]; 
            field3 = field3.offsetParent;
          }
          return offset3;
        }

        function populateName3(cell3) {
            var str3=cell3.firstChild.nodeValue;
            var s3 = str3.indexOf("_");
            var show3=str3.substring(0,s3);
            inputField3.value = show3;
            clearNames3();  
        }

        function clearNames3() {
            var ind3 = nameTableBody3.childNodes.length;
            for (var i3 = ind3 - 1; i3 >= 0 ; i3--) {
                 nameTableBody3.removeChild(nameTableBody3.childNodes[i3]);
            }
            completeDiv3.style.border = "none";
        }
