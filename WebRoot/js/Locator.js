        var xmlHttp4;
        var completeDiv4;
        var inputField4;
        var nameTable4;
        var nameTableBody4;

        function createXMLHttpRequest4() {
            if (window.ActiveXObject) {
                xmlHttp4 = new ActiveXObject("Microsoft.XMLHTTP");
            }
            else if (window.XMLHttpRequest) {
                xmlHttp4 = new XMLHttpRequest();                
            }
        }

        function initVars4() {
            inputField4 = document.getElementById("names4");        
            nameTable4 = document.getElementById("name_table4");
            completeDiv4 = document.getElementById("popup4");
            nameTableBody4 = document.getElementById("name_table_body4");
        }

        function findNames4() {
            initVars4();
            if (inputField4.value.length > 0) {
                createXMLHttpRequest4();            
                var url4 = "suggest.do?method=suggestLocator&name=" + inputField4.value;                        
                xmlHttp4.open("GET", url4, true);
                xmlHttp4.onreadystatechange = callback4;
                xmlHttp4.send(null);
            } else {
                clearNames4();
            }
        }

        function callback4() {
            if (xmlHttp4.readyState == 4) {
                if (xmlHttp4.status == 200) {
                   setNames4(xmlHttp4.responseXML.getElementsByTagName("name4"));
                } else if (xmlHttp4.status == 204){
                    clearNames4();
                }
            }
        }
        
        function setNames4(the_names) {            
            clearNames4();
            var size4 = the_names.length;
            setOffsets4();

            var row4, cell4, txtNode4;
            for (var i = 0; i < size4; i++) {
                var nextNode4 = the_names[i].firstChild.data;
                row4 = document.createElement("tr");
                cell4 = document.createElement("td");
                
                cell4.onmouseout = function() {this.className='mouseOver';};
                cell4.onmouseover = function() {this.className='mouseOut';};
                cell4.setAttribute("bgcolor", "#FFFAFA");
                cell4.setAttribute("border", "0");
                cell4.onclick = function() { populateName4(this); } ;                             

                txtNode4 = document.createTextNode(nextNode4);
                cell4.appendChild(txtNode4);
                row4.appendChild(cell4);
                nameTableBody4.appendChild(row4);
            }
        }

        function setOffsets4() {
            var end4 = inputField4.offsetWidth;
            var left4 = calculateOffsetLeft4(inputField4);
            var top4 = calculateOffsetTop4(inputField4) + inputField4.offsetHeight;

            completeDiv4.style.border = "black 1px solid";
            completeDiv4.style.left = left4 + "px";
            completeDiv4.style.top = top4 + "px";
            nameTable4.style.width = end4 + "px";
        }
        
        function calculateOffsetLeft4(field4) {
          return calculateOffset4(field4, "offsetLeft");
        }

        function calculateOffsetTop4(field4) {
          return calculateOffset4(field4, "offsetTop");
        }

        function calculateOffset4(field4, attr4) {
          var offset4 = 0;
          while(field4) {
            offset4 += field4[attr4]; 
            field4 = field4.offsetParent;
          }
          return offset4;
        }

        function populateName4(cell4) {
            var str4=cell4.firstChild.nodeValue;
            var s4 = str4.indexOf("_");
            var show4=str4.substring(0,s4);
            inputField4.value = show4;
            clearNames4();  
        }

        function clearNames4() {
            var ind4 = nameTableBody4.childNodes.length;
            for (var i4 = ind4 - 1; i4 >= 0 ; i4--) {
                 nameTableBody4.removeChild(nameTableBody4.childNodes[i4]);
            }
            completeDiv4.style.border = "none";
        }
