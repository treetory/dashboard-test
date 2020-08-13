let chart = {};
let interval = {};

function getData(_type) {

	let requestURL = "../api/"+_type;
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(e) { setDataInChart(e, xhr, _type); };
	xhr.onprogress = function(e) {  /*console.log(e);*/ };
	xhr.onabort = function(e) { /*console.log(e);*/	};
	xhr.ontimeout = function(e) {   /*console.log(e);*/	};
	xhr.open('GET', requestURL, true);
	xhr.setRequestHeader('Accept', "application/json;charset=UTF-8");
	xhr.setRequestHeader('Content-Type', "application/json;charset=UTF-8");
	xhr.send();
}

function setDataInChart(e, xhr, _type) {
    //console.log(e);
    //console.log(xhr);
    //console.log(_chart);
    if (xhr.readyState === XMLHttpRequest.DONE) {

        if (xhr.status === 200) {

            let result = eval(xhr.responseText);
            let columns = new Array();
            for (let i=0; i<result.length; i++) {

                let key = Object.keys(result[i]);
                let values = Object.values(result[i]);
                values[0].unshift(key[0]);
                columns.push(values[0]);

                key = null;
                values = null;
            }

            let types = {};
            let key = new Array();
            for (let i=0; i<result.length; i++) {
                key.push(Object.keys(result[i]));
            }

            switch (_type) {
                case "Spline" :
                    flowChart(_type, columns);
                    break;
                case "step" :
                    for (let i=0; i<key.length; i++) {
                        types[key[i]] = 'step';
                    }
                    loadChart(_type, columns, types);
                    break;
                case "areastep" :
                    for (let i=0; i<key.length; i++) {
                        types[key[i]] = 'area-step';
                    }
                    loadChart(_type, columns, types);
                    break;
                default:
                    loadChart(_type, columns);
                    break;
            }

            key = null;
            types = null;
            columns = null;
            result = null;

        } else {
            console.log(xhr);
        }

    };
}

function loadChart(_type, columns, types) {
    chart[_type].load({
        columns: columns,
        types: types
    });
}

function flowChart(_type, columns) {
    chart[_type].flow({
        columns: columns,
        duration: 100
    });
}

function _start(_type) {

    interval[_type] = setInterval(function(){
        switch (_type) {
            default:
                getData(_type);
                break;
        }
        }, 1000);

}

function start() {

    for (let key in chart) {
        _start(key);
    }
}

function _stop(_type) {
	if (interval[_type] !== undefined || interval[_type] !== null) {
        clearInterval(interval[_type]);
	}
}

function stop() {
    for (let key in chart) {
        _stop(key);
    }
}

window.onload = function(e) {
	
	chart.line =
        c3.generate({
            bindto: '#line',
            data: {
                columns: [
                    ['data1', 30, 200, 100, 400, 150, 250],
                    ['data2', 50, 20, 10, 40, 15, 25]
                ]
            },
            oninit: function() {
                console.log(this.config.bindto + ": have been initialized at "+Date.now());
            },
            onrendered: function() {
                console.log(this.config.bindto + ": have been rendered at "+Date.now());
            }
        });

    chart.timeSeries =
        c3.generate({
            bindto: '#timeSeries',
            data: {
                x: 'x',
                xFormat: '%Y-%m-%d',
                columns: [
                    ['x', '2013-01-01', '2013-01-02', '2013-01-03', '2013-01-04', '2013-01-05', '2013-01-06' ],
                    ['data1', 30, 200, 100, 400, 150, 250],
                    ['data2', 50, 20, 10, 40, 15, 25]
                ]
            },
            axis: {
                x: {
                    type: 'timeseries',
                    tick: {
                        format: '%Y-%m-%d'
                    }
                }
            },
            oninit: function() {
                console.log(this.config.bindto + ": have been initialized at "+Date.now());
            },
            onrendered: function() {
                console.log(this.config.bindto + ": have been rendered at "+Date.now());
            }
        });

    chart.Spline =
        c3.generate({
            bindto: '#Spline',
            data: {
                x: 'x',
                xFormat: '%Y-%m-%d',
                columns: [
                    ['x', '2013-01-01', '2013-01-02', '2013-01-03', '2013-01-04', '2013-01-05', '2013-01-06' ],
                    ['data1', 30, 200, 100, 400, 150, 250],
                    ['data2', 50, 20, 10, 40, 15, 25]
                ],
                type: 'spline'
            },
            axis: {
                x: {
                    type: 'timeseries',
                    tick: {
                        format: '%Y-%m-%d'
                    }
                }
            },
            oninit: function() {
                console.log(this.config.bindto + ": have been initialized at "+Date.now());
            },
            onrendered: function() {
                console.log(this.config.bindto + ": have been rendered at "+Date.now());
            }
        });

    chart.step =
        c3.generate({
            bindto: '#step',
            data: {
                columns: [
                    ['data1', 30, 200, 100, 400, 150, 250],
                    ['data2', 50, 20, 10, 40, 15, 25]
                ]
            },
            oninit: function() {
                console.log(this.config.bindto + ": have been initialized at "+Date.now());
            },
            onrendered: function() {
                console.log(this.config.bindto + ": have been rendered at "+Date.now());
            }
        });

    chart.areastep =
        c3.generate({
            bindto: '#areastep',
            data: {
                columns: [
                    ['data1', 30, 200, 100, 400, 150, 250],
                    ['data2', 50, 20, 10, 40, 15, 25]
                ]
            },
            oninit: function() {
                console.log(this.config.bindto + ": have been initialized at "+Date.now());
            },
            onrendered: function() {
                console.log(this.config.bindto + ": have been rendered at "+Date.now());
            }
        });

    chart.multipleXY = c3.generate({
        bindto: '#multipleXY',
        data: {
            xs: {
                'data1': 'x1',
                'data2': 'x2',
            },
            columns: [
                ['x1', 10, 30, 45, 50, 70, 100],
                ['x2', 30, 50, 75, 100, 120],
                ['data1', 30, 200, 100, 400, 150, 250],
                ['data2', 20, 180, 240, 100, 190, 300]
            ]
        }
    });
};

window.onunload = function(e) {
	stop();
};