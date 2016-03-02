var isIPhone = (window.navigator.platform != "Win32");
var isAndroid = (window.navigator.userAgent.indexOf('Android')>=0)?true : false;



//var ipVal='http://192.168.1.53:8080/Tour/';
//var ipVal='http://114.215.145.215:8080/Tour/';
//var ipVal='http://114.215.207.173:8080/Tour/';
var ipVal='http://121.43.230.111:8080/tour/';



function zy_for(e, cb){
    var ch;
    if(e.currentTarget)
        ch = e.currentTarget.previousElementSibling;
    else
        ch = e.previousElementSibling;
    if (ch.nodeName == "INPUT") {
        if (ch.type == "checkbox") 
            ch.checked = !ch.checked;
        if (ch.type == "radio" && !ch.checked) 
            ch.checked = "checked";
        
    }
    if (cb) 
        cb(e, ch.checked);
}

function zy_touch(c, f){
    var t = event.currentTarget;
    if(!t.zTouch) {
        t.zTouch = new zyClick(t, f, c); 
        t.zTouch._touchStart(event);
    }
}

function setNum(s){
    return (parseInt(s, 10)>9) ? s : '0'+s;
}

function setNum2(s){
    return (parseInt(s, 10)>9) ? s : '0'+parseInt(s, 10);
}

function getNowDate(){
    var d = new Date();
    var y = d.getFullYear();
    var m = setNum(d.getMonth()+1);
    var dd = setNum(d.getDate());

    return y+"-"+m+"-"+dd;
}

var isPhone = (window.navigator.platform != "Win32");
var isAndroid = (window.navigator.userAgent.indexOf('Android')>=0)?true : false;



