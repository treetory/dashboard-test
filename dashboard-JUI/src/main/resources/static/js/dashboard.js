// Init data
var radarC = [
    { type : "STR", wizard : 0, archer : 0, warrior : 0,  artist : 0 },
    { type : "VIT", warrior : 0, wizard : 0, archer : 0, artist : 0 },
    { type : "DEX", warrior : 0, wizard : 0, archer : 5, artist : 0 },
    { type : "AGI", warrior : 0, wizard : 0, archer : 5, artist : 0 },
    { type : "INT", warrior : 0, wizard : 0, archer : 0, artist : 0 },
    { type : "WIS", warrior : 0, wizard : 0, archer : 0, artist : 0 }
];
var donutC =  [
    { ie : 70, ff : 11, chrome : 9, safari : 6, other : 4 }
];
var lineC = [
    { apple : 26.1, microsoft : 24.86, oracle : 22.08 },
    { apple : 43.83, microsoft : 25.14, oracle : 30.15 },
    { apple : 55.03, microsoft : 24, oracle : 24.88 },
    { apple : 72.95, microsoft : 25.39, oracle : 32.78 }
];
var thrBarC = [
    { sales: 12, profit: 10, total: 20 },
    { sales: 15, profit: 6, total: 20 },
    { sales: 8, profit: 10, total: 20 },
    { sales: 18, profit: 5, total: 20 }
];
var basicBarC = [
    { quarter : "1Q", sales : 50, profit : 35 },
    { quarter : "2Q", sales : -20, profit : -30 },
    { quarter : "3Q", sales : 10, profit : -5 },
    { quarter : "4Q", sales : 30, profit : 25 }
];
var basicBubbleC =  [
    { quarter : "1Q", sales : 40, profit : 35 },
    { quarter : "2Q", sales : 10, profit : 5 },
    { quarter : "3Q", sales : 15, profit : 10 },
    { quarter : "4Q", sales : 30, profit : 25 }
];


// Access to Ajax
function accessAjax( chart_name, reqURL ) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(e) {
        if (xhr.readyState ===XMLHttpRequest.DONE) {
            //console.log(xhr.responseText);
            if (xhr.status === 200) {

                // Rendering
                chart_name.axis(0).update(eval(xhr.responseText));

            }
        }else {
            console.log('There was a problem with the request.');
        }
    }
    xhr.onprogress = function(e) {console.log('** The request was onprogress');};
    xhr.onabort = function(e) {console.log('** The request was aborted');};
    xhr.ontimeout = function(e) {console.log('** The request was ontimeout');};
    xhr.open('GET', reqURL, false);
    xhr.setRequestHeader('Accept', "application/json;charset=UTF-8");
    xhr.setRequestHeader('Content-Type', "application/json;charset=UTF-8");
    xhr.send();
}


// init chart build
var chart = jui.include("chart.builder");

// Radar Chart
var Radar_chart_v = chart("#chart_1", {
    axis : {
        c : {
            type : "radar",
            domain : "type"
        },
        data : radarC
    },
    brush : {
        type : "path",
        target : [ "warrior", "wizard", "archer", "artist" ]
    }
});

// Donut Chart
var names = {
    ie: "IE",
    ff: "Fire Fox",
    chrome: "Chrome",
    safari: "Safari",
    other: "Others"
};
var Donut_Chart_v = chart("#chart_2", {
    padding : 150,
    axis : {
        data : donutC
    },
    brush : {
        type : "donut",
        showText : "inside",
        format : function(k, v) {
            return v;
        }
    },
    widget : [{
        type : "title",
        text : "Donut Sample"
    }, {
        type : "tooltip",
        orient : "left",
        format : function(data, k) {
            return {
                key: names[k],
                value: data[k]
            }
        }
    }, {
        type : "legend",
        format : function(k) {
            return names[k];
        }
    }]
});

// Line Chart
var Line_Chart_v = chart("#chart_3", {
    axis : {
        x : {
            type : "fullblock",
            domain : [ "2010", "2011", "2012", "2013" ],
            line : true
        },
        y : {
            type : "range",
            domain : function(d) {
                return Math.max(d.apple, d.microsoft, d.oracle);
            },
            step : 10
        },
        data : lineC
    },
    brush : [{
        type : "line",
        animate : true
    }, {
        type : "scatter",
        hide : true
    }],
    widget : [
        { type : "title", text : "Line Sample" },
        { type : "legend" },
        { type : "tooltip", brush : 1 }
    ]
});

//Basic 3D Bar
var Thr_Bar_Chart_v = chart("#chart_4", {
    axis : {
        x : {
            type : "range",
            domain : "total"
        },
        y : {
            type : "block",
            domain : [ "Q1", "Q2", "Q3", "Q4" ]
        },
        c : {
            type : "grid3d"
        },
        data : thrBarC,
        depth : 20,
        degree : 30
    },
    brush : {
        type : "bar3d",
        outerPadding : 10,
        innerPadding : 5
    },
    widget : [{
        type : "tooltip"
    }, {
        type : "title",
        text : "3D Bar Sample"
    }]
});

//Basic Bar
var Bar_Chart_v = chart("#chart_5", {
    axis : {
        x : {
            type : "range",
            domain : [ -40, 60 ],
            step : 10,
            line : true
        },
        y : {
            type : "block",
            domain : "quarter",
            line : true
        },
        data :basicBarC
    },
    brush : {
        type : "bar",
        target : [ "sales", "profit"]
    },
    widget : [
        { type : "title", text : "Bar Sample" },
        { type : "tooltip", orient: "right" },
        { type : "legend" }
    ]
});

//Basic Bubble
var Bubble_Chart_v = chart("#chart_6", {
    axis : {
        x : {
            type : "block",
            domain : "quarter",
            line : true
        },
        y : {
            type : "range",
            domain : [ 0, 50 ],
            step : 10,
            line : true
        },
        data : basicBubbleC
    },
    brush : {
        type : "bubble",
        min : 30,
        max : 50,
        target : "sales",
        scaleKey : "profit",
        showText : true,
        format : function(d) {
            return d.profit;
        },
        colors : function(d) {
            if(d.profit > 30) return 2;
            else if(d.profit > 20) return 1;
            return 0;
        }
    }
});


// Interval Set
function interval_Set( chart_name, chart_location, int_time) {
    setInterval(function(){
        accessAjax( chart_name, chart_location );
    }, int_time);
}


// Final View
window.onload = function(e) {

    // Rendering
    interval_Set( Radar_chart_v, "../api/radar", 1000);
    interval_Set( Donut_Chart_v, "../api/donut", 1000);
    interval_Set( Line_Chart_v, "../api/line", 1000);
    interval_Set( Thr_Bar_Chart_v, "../api/thr_bar", 1000);
    interval_Set( Bar_Chart_v, "../api/bas_bar", 1000);
    interval_Set( Bubble_Chart_v, "../api/bubble", 1000);

};

