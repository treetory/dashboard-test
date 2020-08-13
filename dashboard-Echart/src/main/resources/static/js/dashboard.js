
let _interval;
let stompClient = {};
function getData(chart, requestURL) {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(e) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
	      if (xhr.status === 200) {
	    	  let result = eval(xhr.responseText);
	    	  render(chart, result);
	      }
		};
	};
	xhr.onprogress = function(e) {
		//console.log(e);
	};
	xhr.onabort = function(e) {
		console.log(e);
	};
	xhr.ontimeout = function(e) {
		console.log(e);
	};
	xhr.open('GET', requestURL, false);
	xhr.setRequestHeader('Accept', "application/json;charset=UTF-8");
	xhr.setRequestHeader('Content-Type', "application/json;charset=UTF-8");
	xhr.send();
	
}

function render(chart, data) {
	
	let render_result = {};
	render_result.start = Date.now();
	render_result.start_str = new Date(render_result.start);
	
	let valueSet = new Array();
	let option = chart.getOption();
	let len = option.series.length;
	
	for (var i=0; i<data.length; i++) {
		
		switch (option.series[0].type) {
		case "bar":
			
			if(len>1){
				valueSet = Object.values(data[i]);
				option.series[i].data = valueSet[i];
			}else{
				valueSet.push(data[i]);
				option.series[0].data[i] = valueSet[i];
			}
			break;
		case "pie":
			valueSet.push(data[i]);
			option.series[0].data[i].value = valueSet[i];
			break;
		case "radar":
			valueSet = Object.values(data[i]);
			option.series[0].data[i].value = valueSet[i];
			break;
		case "gauge":
			valueSet.push(data[i]);
			option.series[0].data[i].value = valueSet[i];
			break;
		case "line":
			if(len>1){
				valueSet = Object.values(data[i]);
				option.series[i].data = valueSet[i];
			}else{
				valueSet.push(data[i]);
				option.series[0].data[i] = valueSet[i];
			}
			break;
		}
	}
	chart.setOption(option);
	
	chart.on('finished', function () {
	    //console.log(Date.now());
		
		render_result.chart = option.series[0].type;
		render_result.end = Date.now();
		render_result.end_str = new Date(render_result.end);
		render_result.time = render_result.end - render_result.start;
		console.log(render_result);
	});
	
}


//WebSocket 
//var stompClient = null;
//var socket = null;
function connect(chart, URI) {
	let socket= new SockJS('/gs-guide-websocket');
    stompClient[URI] = Stomp.over(socket);
    stompClient[URI].connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient[URI].subscribe('/topic/'+URI, function (data) {  
            //showGreeting(JSON.parse(greeting.body).content);
        	//console.log(data);
        	let result = eval(data.body);
	    	render(chart, result);
        	
        });
       
    });
    
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
        socket.close();
        socket = null;
    }
    console.log("Disconnected");
}

function sendName(requestURL) {
    //stompClient.send("/socket/test", {}, JSON.stringify({'name': $("#name").val()}));
	switch(requestURL){
	case 'testData1':
		stompClient['testData1'].send("/socket/EchartTest1", {}, JSON.stringify("aaa"));
		break;
	case 'testData2':
		stompClient['testData2'].send("/socket/EchartTest2", {}, JSON.stringify("bbb"));
		break;
	case 'testData3':
		stompClient['testData3'].send("/socket/EchartTest3", {}, JSON.stringify("ccc"));
		break;
	case 'testData4':
		stompClient['testData4'].send("/socket/EchartTest4", {}, JSON.stringify("ddd"));
		break;
	case 'testData5':
		stompClient['testData5'].send("/socket/EchartTest5", {}, JSON.stringify("eee"));
		break;
	case 'testData6':
		stompClient['testData6'].send("/socket/EchartTest6", {}, JSON.stringify("fff"));
		break;
		
	}
}

function gridGetData(gridObj, requestURL) {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(e) {
		if (xhr.readyState === XMLHttpRequest.DONE) {
	      if (xhr.status === 200) {
	    	  let result = eval(xhr.responseText);
	    	  gridRender(gridObj, result);
	      }
		};
	};
	xhr.onprogress = function(e) {
		//console.log(e);
	};
	xhr.onabort = function(e) {
		console.log(e);
	};
	xhr.ontimeout = function(e) {
		console.log(e);
	};
	xhr.open('GET', requestURL, false);
	xhr.setRequestHeader('Accept', "application/json;charset=UTF-8");
	xhr.setRequestHeader('Content-Type', "application/json;charset=UTF-8");
	xhr.send();
	
}
        
window.onload = function(e){
        	
	var myChart = echarts.init(document.getElementById('main'));

    var mOption = {
    	   
    	    legend: {
    	        orient: 'vertical',
    	        x: 'left',
    	        data:['50.100.100.151','172.16.0.16','172.16.0.11','172.16.0.15','172.16.0.21']
    	    },
    	    series: [
    	        {
    	            name:'목적지 ip',
    	            type:'pie',
    	            radius: ['50%', '70%'],
    	            avoidLabelOverlap: false,
    	            stillShowZeroSum: true,
    	            label: {
    	                normal: {
    	                    show: true,
    	                    position: 'center',
    	                    formatter: "{d}%"
    	                },
    	                emphasis: {
    	                    show: true,
    	                    textStyle: {
    	                        fontSize: '17',
    	                        fontWeight: 'bold'
    	                    }
    	                }
    	            },
    	            labelLine: {
    	                normal: {
    	                    show: false
    	                }
    	            },
    	            
    	            data:[
    	                {value:335, name:'50.100.100.151'},
    	                {value:310, name:'172.16.0.16'},
    	                {value:234, name:'172.16.0.11'},
    	                {value:135, name:'172.16.0.15'},
    	                {value:1548, name:'172.16.0.21'}
    	            ]
    	        }
    	    ]
    	};

    myChart.setOption(mOption);
    
    var app = echarts.init(document.getElementById('target'));
    
    var targetOption = {
    	    title: {
    	        text: 'TOP5'
    	    },
    	    tooltip: {
    	        trigger: 'axis',
    	        axisPointer: {
    	            type: 'shadow'
    	        }
    	    },
    	    legend: {
    	        data: ['최근 7일']
    	    },
    	    grid: {
    	        left: '3%',
    	        right: '4%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    xAxis: {
    	        type: 'value',
    	        boundaryGap: [0, 0.01]
    	    },
    	    yAxis: {
    	        type: 'category',
    	        data: ['VIRUS','NAC','VPN','FW']
    	    },
    	    series: [
    	        {
    	            name: 'TOP5',
    	            type: 'bar',
    	            data: [18203, 23489, 29034, 104970]
    	        }
    	    ]
    	};
    
    app.setOption(targetOption);
    
    var appchart = echarts.init(document.getElementById('area'));
    
    var appOption =  {
    	    title: {
    	        text: '심각도'
    	    },
    	    tooltip: {
    	    	trigger: 'item',
    	    	show: true
    	    },
    	    legend: {
    	    	x: 'right',
    	    	y: 'bottom',
    	        data: ['전월', '당월']
    	    },
    	    radar: {
    	        // shape: 'circle',
    	        name: {
    	            textStyle: {
    	                color: '#fff',
    	                backgroundColor: '#999',
    	                borderRadius: 3,
    	                padding: [3, 5]
    	           }
    	        },
    	        indicator: [
    	           { name: 'emergency', max: 50000},
    	           { name: 'critical', max: 50000},
    	           { name: 'warning', max: 50000},
    	           { name: 'notice', max: 50000},
    	           { name: 'info', max: 50000},
    	           { name: 'debug', max: 50000}
    	        ]
    	    },
    	    series: [{
    	        name: '전월 vs 당월',
    	        type: 'radar',
    	        // areaStyle: {normal: {}},
    	        //itemStyle: {normal: {areaStyle: {type: 'default'}}},
    	        data : [
    	            {
    	                value : [4300, 10000, 28000, 35000, 50000, 19000],
    	                name : '전월',
    	                areaStyle: {
    	                	normal: {
    	                		opacity: 0.9,
    	                		color: new echarts.graphic.RadialGradient(0.5, 0.5, 1, [
    	                			{
    	                				color: '#FFD8D8',
    	                				offset: 0
    	                			},
    	                			{
    	                				color: '#F15F5F',
    	                				offset: 1
    	                			}
    	                		])
    	                	}
    	                }
    	            },
    	             {
    	                value : [5000, 14000, 28000, 31000, 42000, 21000],
    	                name : '당월',
    	                areaStyle: {
    	                	normal: {
    	                		opacity: 0.9,
    	                		color: new echarts.graphic.RadialGradient(0.5, 0.5, 1, [
    	                			{
    	                				color: '#DAD9FF',
    	                				offset: 0
    	                			},
    	                			{
    	                				color: '#050099',
    	                				offset: 1
    	                			}
    	                		])
    	                	}
    	                }
    	            }
    	        ]
    	    }]
    	};
    
    appchart.setOption(appOption);
    
    
    
    var gaugeChart = echarts.init(document.getElementById('gauge'));
    
    var gaugeOption = {
    	    tooltip : {
    	        formatter: "{a} <br/>{b} : {c}%"
    	    },
    	    toolbox: {
    	        feature: {
    	            restore: {},
    	            saveAsImage: {}
    	        }
    	    },
    	    series: [
    	        {
    	            name: 'gauge',
    	            type: 'gauge',
    	            radius: '75%',
    	            startAngle: 180,
    	            endAngle: 0,
    	            min: 0,
    	            max: 100,
    	            axisLabel: {
    	                show: false
    	            },
    	            axisTick: {
    	              show: true,
    	              splitNumber: 5,
    	              length: 30
    	            },
    	            detail: {formatter:'{value}%'},
    	            data: [{value: 50, name: '경보단계'}],
    	            splitNumber: 1,
    	            axisLine: {
    	                show: true,
    	                lineStyle: {       
    	                    color: [[0.2, '#91c7ae'], [0.4, '#63869e'], [0.6, '#E8DB5F'], [0.8, '#F09661'], [1, '#c23531']],
    	                    width: 30
    	                }
    	            },
    	        }
    	    ]
    	};
    
    gaugeChart.setOption(gaugeOption);
    
    /*setInterval(function(){
    	gaugeOption.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
    	gaugeChart.setOption(gaugeOption, true);
    }, 2000);*/
    
    
    
    var areaChart = echarts.init(document.getElementById('stackedArea'));
    
    var areaOption = {
    	    title: {
    	       
    	    },
    	    tooltip : {
    	        trigger: 'axis',
    	        axisPointer: {
    	            type: 'cross',
    	            label: {
    	                backgroundColor: '#6a7985'
    	            }
    	        }
    	    },
    	    legend: {
    	        data:['심각','경고','주의','양호','안전']
    	    },
    	    toolbox: {
    	        feature: {
    	            saveAsImage: {}
    	        }
    	    },
    	    grid: {
    	        left: '3%',
    	        right: '4%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    xAxis : [
    	        {
    	            type : 'category',
    	            boundaryGap : false,
    	            data : ['11/12','11/13','11/14','11/15','11/16','11/17','11/18']
    	        }
    	    ],
    	    yAxis : [
    	        {
    	            type : 'value'
    	        }
    	    ],
    	    series : [
    	        {
    	            name:'심각',
    	            type:'line',
    	            stack: '총합',
    	            areaStyle: {},
    	            data:[120, 132, 101, 134, 90, 230, 210]
    	        },
    	        {
    	            name:'경고',
    	            type:'line',
    	            stack: '총합',
    	            areaStyle: {},
    	            data:[220, 182, 191, 234, 290, 330, 310]
    	        },
    	        {
    	            name:'주의',
    	            type:'line',
    	            stack: '총합',
    	            areaStyle: {},
    	            data:[150, 232, 201, 154, 190, 330, 410]
    	        },
    	        {
    	            name:'양호',
    	            type:'line',
    	            stack: '총합',
    	            areaStyle: {normal: {}},
    	            data:[320, 332, 301, 334, 390, 330, 320]
    	        },
    	        {
    	            name:'안전',
    	            type:'line',
    	            stack: '총합',
    	            label: {
    	                normal: {
    	                    show: true,
    	                    position: 'top'
    	                }
    	            },
    	            areaStyle: {normal: {}},
    	            data:[820, 932, 901, 934, 1290, 1330, 1320]
    	        }
    	    ]
    	};
    
    areaChart.setOption(areaOption);
    
    
    var stackBarChart = echarts.init(document.getElementById('stackBar'));
    
    var stackBarOption = {
    	    tooltip : {
    	        trigger: 'axis',
    	        axisPointer : {            
    	            type : 'shadow'        
    	        }
    	    },
    	    legend: {
    	        data: ['VPN', 'FW','NAC','VIRUS','SYSTEM']
    	    },
    	    grid: {
    	        left: '3%',
    	        right: '4%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    xAxis:  {
    	    	type: 'category',
    	        data: ['1월','2월','3월','4월','5월','6월','7월']
    	    },
    	    yAxis: {
    	        type: 'value'
    	    },
    	    series: [
    	        {
    	            name: 'VPN',
    	            type: 'bar',
    	            stack: '총합',
    	            label: {
    	                normal: {
    	                    show: true,
    	                    position: 'insideRight'
    	                }
    	            },
    	            data: [320, 302, 301, 334, 390, 330, 320]
    	        },
    	        {
    	            name: 'FW',
    	            type: 'bar',
    	            stack: '총합',
    	            label: {
    	                normal: {
    	                    show: true,
    	                    position: 'insideRight'
    	                }
    	            },
    	            data: [120, 132, 101, 134, 90, 230, 210]
    	        },
    	        {
    	            name: 'NAC',
    	            type: 'bar',
    	            stack: '총합',
    	            label: {
    	                normal: {
    	                    show: true,
    	                    position: 'insideRight'
    	                }
    	            },
    	            data: [220, 182, 191, 234, 290, 330, 310]
    	        },
    	        {
    	            name: 'VIRUS',
    	            type: 'bar',
    	            stack: '총합',
    	            label: {
    	                normal: {
    	                    show: true,
    	                    position: 'insideRight'
    	                }
    	            },
    	            data: [150, 212, 201, 154, 190, 330, 410]
    	        },
    	        {
    	            name: 'SYSTEM',
    	            type: 'bar',
    	            stack: '총합',
    	            label: {
    	                normal: {
    	                    show: true,
    	                    position: 'insideRight'
    	                }
    	            },
    	            data: [820, 832, 901, 934, 1290, 1330, 1320]
    	        }
    	    ]
    	};
    
    
    stackBarChart.setOption(stackBarOption);
    
   
    
    //_interval = setInterval(function(){
//		getData(myChart, "../api/EchartTest1");
//		getData(app, "../api/EchartTest2");
//		getData(appchart, "../api/EchartTest3");
//		getData(gaugeChart, "../api/EchartTest4");
//		getData(areaChart, "../api/EchartTest5");
//		getData(stackBarChart, "../api/EchartTest6");
	//}, 3000);
    
    
 /*  웹소켓 연결 시작 */   
    	connect(myChart, 'testData1');
        connect(app, 'testData2');
        connect(appchart, 'testData3');
        connect(gaugeChart, 'testData4');
        connect(areaChart, 'testData5');
        connect(stackBarChart, 'testData6');
	   
        setInterval(function(){
        //setTimeout(function(){
        	sendName('testData1');
        	sendName('testData2');
        	sendName('testData3');
        	sendName('testData4');
        	sendName('testData5');
        	sendName('testData6');
        }, 3000);

        
        $(function () {
            var gridsample = $('#w2ui_grid').w2grid({
                name: 'grid',
                show: {
                	toolbar: false,
                	footer: true,
                	toolbarAdd: false,
                	toolbarDelete: false,
                	toolbarSave: false,
                	toolbarEdit: false,
                },
                header: 'List of Names',
                columns: [
                    { field: 'fname', caption: 'First Name', size: '30%', sortable: true },
                    { field: 'lname', caption: 'Last Name', size: '30%', sortable: true },
                    { field: 'email', caption: 'Email', size: '40%', sortable: true },
                    { field: 'sdate', caption: 'Start Date', size: '120px', sortable: true }
                ],
                records: [
                    { recid: 1, fname: "Peter", lname: "Jeremia", email: 'peter@mail.com', sdate: '2/1/2010' },
                    { recid: 2, fname: "Bruce", lname: "Wilkerson", email: 'bruce@mail.com', sdate: '6/1/2010' },
                    { recid: 3, fname: "John", lname: "McAlister", email: 'john@mail.com', sdate: '1/16/2010' },
                    { recid: 4, fname: "Ravi", lname: "Zacharies", email: 'ravi@mail.com', sdate: '3/13/2007' },
                    { recid: 5, fname: "William", lname: "Dembski", email: 'will@mail.com', sdate: '9/30/2011' },
                    { recid: 6, fname: "David", lname: "Peterson", email: 'david@mail.com', sdate: '4/5/2010' },
                    { recid: 7, fname: "Peter", lname: "Jeremia", email: 'peter@mail.com', sdate: '2/1/2010' },
                    { recid: 8, fname: "Bruce", lname: "Wilkerson", email: 'bruce@mail.com', sdate: '6/1/2010' },
                    { recid: 9, fname: "John", lname: "McAlister", email: 'john@mail.com', sdate: '1/16/2010' },
                    { recid: 10, fname: "Ravi", lname: "Zacharies", email: 'ravi@mail.com', sdate: '3/13/2007' },
                    { recid: 11, fname: "William", lname: "Dembski", email: 'will@mail.com', sdate: '9/30/2011' },
                    { recid: 12, fname: "David", lname: "Peterson", email: 'david@mail.com', sdate: '4/5/2010' }
                ], 
                onClick: function(event){
                	var recid = this.get(event.recid);
                	$('#label').text("이름: "+ recid.fname+ ", 이메일: "+ recid.email);
                	window.open("http://www.naver.com");
                }
            });
           
        });
        
        setInterval(function(){
        
        	w2ui['grid'].add([
        		{recid: 13, fname: 'Susan', lname: 'Silver', email: 'susan@mail.com', sdate: '2/23/2019'},
        		{recid: 13, fname: 'Kelly', lname: 'Miller', email: 'susan@mail.com', sdate: '3/23/2019'},
        		{recid: 13, fname: 'Francis', lname: 'Gatos', email: 'susan@mail.com', sdate: '4/21/2019'},
        		{recid: 13, fname: 'Harry', lname: 'Potter', email: 'susan@mail.com', sdate: '5/22/2019'},
        		{recid: 13, fname: 'Ron', lname: 'Wizley', email: 'susan@mail.com', sdate: '6/18/2019'},
        		{recid: 13, fname: 'Hermione', lname: 'Silver', email: 'susan@mail.com', sdate: '7/16/2019'},
        		{recid: 13, fname: 'Snape', lname: 'Denver', email: 'susan@mail.com', sdate: '8/18/2019'},
        		{recid: 13, fname: 'Dumbledore', lname: 'Silver', email: 'susan@mail.com', sdate: '9/17/2019'},
        		{recid: 13, fname: 'Winnie', lname: 'Silver', email: 'susan@mail.com', sdate: '10/23/2019'},
        		{recid: 13, fname: 'Jack', lname: 'Bob', email: 'susan@mail.com', sdate: '11/14/2019'},
        		{recid: 13, fname: 'Carl', lname: 'Martin', email: 'susan@mail.com', sdate: '12/23/2019'},
        		{recid: 13, fname: 'Jennifer', lname: 'Stone', email: 'susan@mail.com', sdate: '1/13/2019'},
        		], [true]);
        },3000);
      
}; 



