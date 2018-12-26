$(function() {
	var testList = new Array() ;
    for(var i=1; i<=2; i++){
        
        // 객체 생성
        var data = new Object() ;
         
        data.period = i ;
        data.profit = i ;
         
         
        // 리스트에 생성된 객체 삽입
        testList.push(data) ;
    }
    var jsonData = JSON.stringify(testList) ;
    Morris.Area({
        element: 'morris-area-chart',
        data:[{period:1, profit:2}], 
        xkey: 'period',
        ykeys: ['profit'],
        labels: ['profit'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });

    $.extend(Area, jsonData);
       
        

});
