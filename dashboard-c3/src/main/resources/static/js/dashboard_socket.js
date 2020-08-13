let chart = {};
let stompClient = {};
let interval = {};
//let columns = /*new Array()*/{};
//let key = {};
//let values = {};

function connect(_type) {

    let socket = new SockJS('/gs-guide-websocket');
    stompClient[_type] = Stomp.over(socket);

    stompClient[_type].heartbeat.outgoing = 20000; // client will send heartbeats every 20000ms
    stompClient[_type].heartbeat.incoming = 0;     // client does not want to receive heartbeats from the server

    stompClient[_type].connect({}, function (frame) {
        //console.log('Connected: ' + frame);
        stompClient[_type].subscribe('/topic/'+_type, function (data) {
            loadChart(_type, eval(data.body));
        });
    });

}

function disconnect(_type) {
    if (stompClient[_type] !== undefined || stompClient[_type] !== null) {
        stompClient[_type].disconnect();
    }
    console.log(_type + " is disconnected.");
}

function sendName(_type, message) {
    stompClient[_type].send("/socket/"+_type, {}, JSON.stringify(message));
}

function _start(_type) {

    connect(_type);

    interval[_type] = setInterval(function(){
        sendName(_type, { name : _type });
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
    disconnect(_type);
}

function stop() {
    for (let key in chart) {
        _stop(key);
    }
}

function loadChart(_type, result) {
    /*
    columns[_type] = new Array();
    for (let i=0; i<result.length; i++) {
        //console.log(Object.values(result[i]));
        key = Object.keys(result[i]);
        values = Object.values(result[i]);

        values[0].unshift(key[0]);

        columns[_type].push(values[0]);
        //console.log(columns);
    }
    chart[_type].load({
        columns: columns[_type]
    });

    key = null;
    values = null;
    columns[_type] = null;
    */
    let columns = new Array();
    for (let i=0; i<result.length; i++) {

        let key = Object.keys(result[i]);
        let values = Object.values(result[i]);
        values[0].unshift(key[0]);
        columns.push(values[0]);

        key = null;
        values = null;
    }
    chart[_type].load({
        columns: columns
    });

    columns = null;
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

};

window.onunload = function(e) {
    stop();
};