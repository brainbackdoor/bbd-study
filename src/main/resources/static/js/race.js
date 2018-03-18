(function() {
var count = 0;
var carCount;
var interval;
var httpRequest;
var carNames;
var carPositions;

var racego = document.getElementById('racego');
var resultDom = document.getElementById('result');
var winner = document.getElementById('winner');

if (racego) {
    racego.addEventListener("click", move);
}

function move() {
    count = document.getElementById("count").value;
    console.log("count : " + count);
    
    interval = setInterval(ajaxCall, 300);
    function ajaxCall() {
        count--;
        if (count < 0) {
            clearInterval(interval);
            window.alert("경기가 끝났습니다. 축하드립니다 !!!");
            return;
        };
        $.ajax({
            type : 'get',
            url : '/api/move',
            dataType : 'json',
            error: function() {
                console.log('failure')
            },
            success : function(result) {
                if (result.status) {
                    
                    carCount = result.cars.count;
                    
                    for (var i = 0; i < carCount; i++) {
                        
                        var lineDiv = document.createElement('div');
                        var nameSpan = document.createElement('div');
                        var positionSpan = document.createElement('div');
                        nameSpan.className = "name";
                        positionSpan.className = "position";
                        
                        lineDiv.appendChild(nameSpan);
                        lineDiv.appendChild(positionSpan);
                        
                        resultDom.appendChild(lineDiv);
                    }

                    carNames = document.getElementsByClassName('name');
                    carPositions = document.getElementsByClassName('position');
                    
                    for (var i = 0; i < carCount; i++) {
                        carNames[i].innerHTML = result.cars.cars[i].name;
                        carPositions[i].innerHTML = result.cars.cars[i].htmlPosition;
                    }
                    
                } else {
                    window.alert(result.errorMessage);
                }
            }
        });
    }
}

})();