        var xmlHttp2;
        var completeDiv2;
        var inputField2;
        var nameTable2;
        var nameTableBody2;

        function createXMLHttpRequest2() {
            if (window.ActiveXObject) {
                xmlHttp2 = new ActiveXObject("Microsoft.XMLHTTP");
            }
            else if (window.XMLHttpRequest) {
                xmlHttp2 = new XMLHttpRequest();                
            }
        }

        function initVars2() {
            inputField2 = document.getElementById("names2");  
            nameTable2 = document.getElementById("name_table2");
            completeDiv2 = document.getElementById("popup2");
            nameTableBody2 = document.getElementById("name_table_body2");
        }

        function findNames2() {
            initVars2();
            if (inputField2.value.length > 0) {
                createXMLHttpRequest2();       
                var url2 = "suggest.do?method=suggestStaORName&staffer=" + inputField2.value+"&tempid="+Math.random() * 10000;                        
                xmlHttp2.open("GET", url2, true);
                xmlHttp2.onreadystatechange = callback2;
                xmlHttp2.send(null);
            } else {
                clearNames2();
            }
        }

        function callback2() {
            if (xmlHttp2.readyState == 4) {
                if (xmlHttp2.status == 200) {
                   setNames2(xmlHttp2.responseXML.getElementsByTagName("name2"));
                } else if (xmlHttp2.status == 204){
                    clearNames2();
                }
            }
        }
        
        function setNames2(the_names) {            
            clearNames2();
            var size2 = the_names.length;
            setOffsets2();

            var row2, cell2, txtNode2;
            for (var i = 0; i < size2; i++) {
                var nextNode2 = the_names[i].firstChild.data;
                row2 = document.createElement("tr");
                cell2 = document.createElement("td");
                
                cell2.onmouseout = function() {this.className='mouseOver';};
                cell2.onmouseover = function() {this.className='mouseOut';};
                cell2.setAttribute("bgcolor", "#FFFAFA");
                cell2.setAttribute("border", "0");
                cell2.onclick = function() { populateName2(this); } ;                             

                txtNode2 = document.createTextNode(nextNode2);
                cell2.appendChild(txtNode2);
                row2.appendChild(cell2);
                nameTableBody2.appendChild(row2);
            }
        }

        function setOffsets2() {
            var end2 = inputField2.offsetWidth;
            var left2 = calculateOffsetLeft2(inputField2);
            var top2 = calculateOffsetTop2(inputField2) + inputField2.offsetHeight;

            completeDiv2.style.border = "black 1px solid";
            completeDiv2.style.left = left2 + "px";
            completeDiv2.style.top = top2 + "px";
            nameTable2.style.width = end2 + "px";
        }
        
        function calculateOffsetLeft2(field2) {
          return calculateOffset2(field2, "offsetLeft");
        }

        function calculateOffsetTop2(field2) {
          return calculateOffset2(field2, "offsetTop");
        }

        function calculateOffset2(field2, attr2) {
          var offset2 = 0;
          while(field2) {
            offset2 += field2[attr2]; 
            field2 = field2.offsetParent;
          }
          return offset2;
        }

        function populateName2(cell2) {
            var str2=cell2.firstChild.nodeValue;
            var s2 = str2.indexOf("_");
            var show2=str2.substring(0,s2);
            inputField2.value = show2;
            clearNames2();  
        }

        function clearNames2() {
            var ind2 = nameTableBody2.childNodes.length;
            for (var i2 = ind2 - 1; i2 >= 0 ; i2--) {
                 nameTableBody2.removeChild(nameTableBody2.childNodes[i2]);
            }
            completeDiv2.style.border = "none";
        }
