/**/_jsload2&&_jsload2('vectordrawlib', 'function If(a){this.dI=o;this.k=x.object.extend(a||{},{Ch:o});Gc.call(this,this.k);this.xg={};this.loaded=q;this.Jt=p;this.$B=q;this.ZJ={road:"rd",water:"wt",building:"bd",land:"ld",government:"gv",point:"pts"};this.UE={market:"mt",food:"fd",communications:"cm",hotel:"ht",attractions:"at",recreation:"rc"}}If.prototype=new Gc; If.prototype.ra=function(a){if(!this.loaded){this.loaded=o;var b=this;b.map=a;b.eb=b.map.eb;b.map=a;b.Zw=q;b.zK=p;b.re="df";b.k.poiElements&&b.k.poiElements.name&&(b.re=b.UE[b.k.poiElements.name]);b.Re=b.k.style||"normal";b.mn=b.k.styleStr||p;b.hq=200;b.Be=p;b.ae=0;b.xb=this.eb.On(0);b.Nd=this.eb.On(10);b.eb.ld.appendChild(this.xb);b.eb.ld.appendChild(this.Nd);b.eb.xb=b.xb;b.eb.Nd=this.Nd;b.Kb=new z.VectorDrawLib;b.ks="";b.k.features&&(b.ks=b.XW(b.k.features));b.Kb.oC=b.Re;b.bm();b.Re&&"normal"!== b.Re||b.mn&&0<b.mn.length?b.St(b.Re,b.mn,function(){b.map.addEventListener("click",function(a){b.ae++;if(b.ae===1)b.Be=setTimeout(function(){b.vu(a);b.ae=0},b.hq);else{clearTimeout(b.Be);b.ae=0;return q}});b.xb.innerHTML="";b.Nd.innerHTML="";b.be(o)}):(b.be(),b.map.addEventListener("click",function(a){b.ae++;if(b.ae===1){if(!a.bb)b.Be=setTimeout(function(){b.vu(a);b.ae=0},b.hq)}else{clearTimeout(b.Be);b.ae=0;return q}}));b.map.C.lj&&(G()&&b.Ff==j)&&(b.Ff=new z.wG(b.map),b.map.Ka(b.Ff))}}; x.extend(If.prototype,{bm:function(){var a=this;setTimeout(function(){a.map.addEventListener("poilayervisiblechange",function(b){a.wV(b)});a.map.addEventListener("moveend",function(){a.be()});a.map.addEventListener("zoomend",function(){a.ty();a.Zw=q;a.be(o)});a.map.addEventListener("onresize",function(){a.be()});Ua()&&(a.map.addEventListener("onmoving",function(){a.be()}),a.map.addEventListener("onmaptypechange",function(){a.be()}));a.map.addEventListener("mousemove",function(b){a.map.Wb()&&a.GC(b)})}, 1);a.map.addEventListener("setcustomstyles",function(b){a.pC(b.target)})},XW:function(a){for(var b="",c=0,d=a.length;c<d;c++)b=b+this.ZJ[a[c]]+",";b&&(this.Kb.bi=b);return b},St:function(a,b,c){if(this.map.Wb()){var d=this,e=z.qc+"custom/",f;b&&0<b.length?(f="setStyle_"+b.length,e+="mapstyle?styles="+encodeURIComponent(b)):(f="setStyle_"+a,e+="getstyle?customid="+a);f+=this.map.ba;window[f]=function(b,e){var k=x.extend({},d.Kb.tz);d.Kb.Fb=x.extend(k,b);k=z.Db.ue(d.Kb.Fb["3181"][1]);d.map.C.Co=k;d.map.La().style.backgroundColor= k;c(a);d.map.dispatchEvent(new M("onsetmapstylesuccess",e));delete window[f]};oa(e+("&callback="+f+"&udt=20150116"),q)}},SN:function(a){if(this.map.Wb()){for(var b="",c=0,d=a.length;c<d;c++)b=b+(this.ZJ[a[c]]||"")+",";b==this.ks&&""==!b||(""==b&&(b="no"),this.ks=b,this.Kb.bi=b,this.Kb.Gs({bg:this.kx(this.xb),poi:this.kx(this.Nd)},this.eb,this.re))}},$N:function(a){if(a.name&&this.UE[a.name]){var b=this.UE[a.name];if(a.styles.visibility==o&&this.re!==b&&-1<this.ks.indexOf("pts")){this.re=b;if(this.Nd)for(var c= [],d=[],a=this.Nd.childNodes,b=0,e=a.length;b<e;b++){var f=a[b].id.split("_");c.push([f[1],f[2]]);d.push(a[b])}this.Kb.YC(c,d,this.re,this.eb,p)}}},Op:function(a){var b=this;b.St(a.style,a.styleStr,function(a){b.Re=a;b.ty();b.Kb.oC=b.Re;var a=b.Kb.Ps,d;for(d in a)delete a[d];"df"!==b.re&&b.eE();b.aF()})},b4:function(a){var b=this,c=[],d=[],e=a.clickFea;b.zK=e;if(a.type){var f=x.$(e.tileId),g=e.tileId;if(b.re==a.type){var i=Math.pow(2,18-b.map.Oa);b.l0();b.xg[g]={canvas:f,fea:e.fea};b.cL(e,i);var c= this.xg,k;for(k in c)d=x.$(k).getContext("2d"),b.Kb.om(d,c[k].fea,i,o);return}b.ty();b.re=a.type;b.xg[g]={canvas:f,fea:e.fea};b.cL(e,i)}else{b.su();if("df"==b.re)return;b.ty();b.re="df"}if(b.Nd){i=b.Nd.childNodes;k=0;for(a=i.length;k<a;k++)f=i[k].id.split("_"),c.push([f[f.length-3],f[f.length-2]]),d.push(i[k])}b.$B||(b.map.addEventListener("onclickicondrawed",function(a){var c=a.tarPoi.id;b.Zw=o;b.xg&&b.xg[c]&&(b.xg[c].fea=a.tarPoi.fea)}),b.$B=o);"df"==b.re?b.su():b.eE();b.Kb.YC(c,d,b.re,b.eb,e)}, l0:function(){var a=this.xg,b=Math.pow(2,18-this.map.Oa),c;for(c in a)this.Kb.om(a[c].canvas.getContext("2d"),a[c].fea,b,q);for(var d in a)delete a[d]},ty:function(){var a=this.xg;try{for(var b in a)delete a[b]}catch(c){}},cL:function(a){var b=a.tileId.split("_"),c=b.length,d=parseInt(b[c-3]),e=parseInt(b[c-2]),c=parseInt(b[c-1]),f=d-1,g=d+1,i=e-1,k=e+1,l=this.map.ba.replace(/^TANGRAM_/,""),b=x.$(l+"_poi_"+f+"_"+e+"_"+c),e=x.$(l+"_poi_"+g+"_"+e+"_"+c),m=x.$(l+"_poi_"+d+"_"+i+"_"+c),d=x.$(l+"_poi_"+ d+"_"+k+"_"+c),n=x.$(l+"_poi_"+f+"_"+i+"_"+c),f=x.$(l+"_poi_"+f+"_"+k+"_"+c),i=x.$(l+"_poi_"+g+"_"+i+"_"+c),c=x.$(l+"_poi_"+g+"_"+k+"_"+c);b&&this.lk(b,a.fea);e&&this.lk(e,a.fea);m&&this.lk(m,a.fea);d&&this.lk(d,a.fea);n&&this.lk(n,a.fea);f&&this.lk(f,a.fea);i&&this.lk(i,a.fea);c&&this.lk(c,a.fea)},lk:function(a,b){var c=this.xQ(a.Zd,b[5].u);c&&(this.xg[a.id]={canvas:a,fea:c})},xQ:function(a,b){try{if(a.length)for(var c=0,d=a.length;c<d;c++){var e=a[c];if(e[5]&&e[5].u&&e[5].u==b)return e}}catch(f){}}, wV:function(a){a.visible==q?(this.eb.ld.removeChild(this.Nd),this.re=""):(this.eb.ld.appendChild(this.Nd),a=this.eb.Dm(this.Nd),this.Kb.YC(a.x4,a.w4,this.re,this.eb))},be:function(a){this.map.fa();if(this.map.Wb()){this.xb.style.display="block";this.Nd.style.display="block";this.Y_(this.xb,this.Nd);this.eb.AK={};var b=this.eb.Dm(this.xb,"bg");poiVectorObj=this.eb.Dm(this.Nd,"poi");for(var c in this.xg)x.$(c)||delete this.xg[c];this.Kb.Gs({bg:b,poi:poiVectorObj,isZoomMap:a?o:q},this.eb,this.re)}else{a= this.xb;b=this.Nd;a.style.display="none";b.style.display="none";c=a.childNodes.length;for(c-=1;0<=c;c--){var d=a.childNodes[c];a.removeChild(d)}c=b.childNodes.length;for(c-=1;0<=c;c--)d=b.childNodes[c],b.removeChild(d)}},kx:function(a){if(a){for(var b=[],a=a.childNodes,c=0,d=a.length;c<d;c++){var e=a[c].id.split("_");b.push([e[e.length-3],e[e.length-2],a[c]])}return b}},aF:function(){this.map.Wb()&&this.Kb.Gs({bg:this.kx(this.xb),poi:this.kx(this.Nd)},this.eb,this.re)},Y_:function(a,b){var c=q;if(a)for(var d= a.childNodes,e=0,f=d.length;e<f;e++)if(d[e].$e==o){c=o;break}if(!c){c=b.childNodes;d=0;for(f=c.length;d<f;d++)c[d].$e=q}},vu:function(a){this.map.C.lj&&(a=this.xs(a),this.map.C.Bw?this.map.C.Bw(a):this.Ob(a))},Ob:function(a){a?(Ua()&&this.Tz(a),G()&&this.Ff&&this.Ff.switchTo(a)):G()&&this.Ff&&this.Ff.U()},GC:function(a){this.map.C.lj&&(this.xs(a)?(this.map.platform.style.cursor="pointer",this.map.R.Sx=o):(this.map.R.Sx=q,this.map.platform.style.cursor!=this.map.C.Yb&&0==this.map.R.Qe.length&&(this.map.platform.style.cursor= this.map.C.Yb)))},Tz:function(a){(a=a.uid)&&cd.cb(s(),{qt:"inf",uid:a,operate:"mapclick",clicktype:"vector"})},YG:function(a,b,c){var d=this;if(b&&b.content){var e=b.content,f=e.pano||0,c=d.map.wb(c.point);if(!g)var g={};g.isFromMPC=o;var i=e.addr,g=e.street_id||"";if(1==e.poiType||3==e.poiType)i=O.unique(i.split(";")).join("; ");var k=e.tel;k&&(k=k.replace(/,/g,", "));d.Yq(e.cla);var l=K("div",{style:"font-size:12px;padding:5px 0;overflow:hidden;*zoom:1;"}),b=q;f&&(360>d.map.height?b=o:(f=[],f.push("<div class=\'panoInfoBox\' id=\'panoInfoBox\' title=\'"+ e.name+"\\u5916\\u666f\' title=\'\\u67e5\\u770b\\u5168\\u666f\' >"),f.push("<img filter = \'pano_thumnail_img\' class=\'pano_thumnail_img\' width=323 height=101 border=\'0\' alt=\'"+e.name+"\\u5916\\u666f\' src=\'"+(z.url.proto+z.url.domain.pano[0]+"/pr/?qt=poiprv&uid="+g+"&width=323&height=101&quality=80&fovx=200")+"\' id=\'pano_"+a+"\'/>"),f.push("<div filter = \'panoInfoBoxTitleBg\' class=\'panoInfoBoxTitleBg\'></div><a href=\'javascript:void(0)\' filter=\'panoInfoBoxTitleContent\' class=\'panoInfoBoxTitleContent\' >\\u8fdb\\u5165\\u5168\\u666f&gt;&gt;</a>"), f.push("</div>"),l.innerHTML=f.join("")));i&&(f=K("p",{style:"padding:0;margin:0;line-height:18px;font-size:12px;color:#4d4d4d;"}),f.innerHTML="\\u5730\\u5740\\uff1a"+i,l.appendChild(f));k&&(f=K("p",{style:"padding:0;margin:0;line-height:18px;font-size:12px;color:#4d4d4d;"}),f.innerHTML="\\u7535\\u8bdd\\uff1a"+k,l.appendChild(f));e.tag&&(k=K("p",{style:"padding:0;margin:0;line-height:18px;font-size:12px;color:#4d4d4d;color:#7f7f7f;"}),k.innerHTML="\\u6807\\u7b7e\\uff1a"+e.tag,l.appendChild(k));a="http://api.map.baidu.com/place/detail?uid="+ a+"&output=html&source=jsapi&operate=mapclick&clicktype=vector";k="<div style=\'height:26px;\'><a href=\'"+a+"\' target=\'_blank\' style=\'font-size:14px;color:#4d4d4d;font-weight:bold;text-decoration:none;\' onmouseover=\'this.style.textDecoration=\\"underline\\";this.style.color=\\"#3d6dcc\\"\' onmouseout =\'this.style.textDecoration=\\"none\\";this.style.color=\\"#4d4d4d\\"\'>"+e.name+"</a>";a=new sc(l,{width:322,enableSearchTool:o,title:k+("<a href=\'"+a+"\' target=\'_blank\' style=\'font-size:12px;color:#3d6dcc;margin-left:5px;text-decoration:none;\' onmouseover=\'this.style.textDecoration=\\"underline\\"\' onmouseout =\'this.style.textDecoration=\\"none\\"\'>\\u8be6\\u60c5&raquo;</a>")+ "</div>",enableParano:b});b&&(a.street_id=g);a.addEventListener("open",function(){var a=x.$("panoInfoBox");if(a){var b=e.street_id||"";d.dW("click",function(){Qa(5052);d.Gq(b)},a,"pano_thumnail_img|panoInfoBoxTitleBg|panoInfoBoxTitleContent")}});this.map.Ob(a,c)}},Gq:function(a){var b=z.rg("pano","scape/")[0],c=this,d=(new Date).getTime(),e="Pano"+d;z[e]=function(a){var b=c.map.xm(),a=a.content[0];b.tc(a.poiinfo.PID);b.show();b.Mc({heading:a.poiinfo.Dir,pitch:a.poiinfo.Pitch})};d=(new Date).getTime(); oa(b+("?qt=poi&udt=20131021&uid="+a+"&t="+d+"&fn=BMap."+e),q)},Yq:function(a){for(var b=[],c=0,d=a.length;c<d;c++)b.push(a[c][1]),c<d-1&&b.push(", ");return b.join("")},xs:function(a){var b=this.Nd.getElementsByTagName("canvas"),c=a.offsetX,d=a.offsetY,e=j,f=j;this.map.Bb();for(var f=this.map.oa().k.Qb,g=0,i=b.length;g<i;g++){var k=this.Pf(b[g]);if(c>k.left&&c<=k.left+f&&d>k.top&&d<=k.top+f){e=b[g];break}}if(e==j||e.Zd==j)return q;f=e.Zd;b=0;for(i=f.length;b<i;b++){var c=f[b],d=c[0],g=c[1],l=this.Kb.Fb[c[3]]|| window.Fb[c[3]],m=l[0],l=this.Kb.Xs(l,this.Kb.Fb[c[4]]||window.Fb[c[4]])[1],n=c[5]||{};if(l==j||"empty"==l)break;if(2!=m&&(3!=m&&4!=m&&0<l.length&&n.u)&&(iconX=g[0]+k.left,iconY=g[1]+k.top,a.offsetX>=iconX-15&&a.offsetX<=iconX+15&&a.offsetY>=iconY-15&&a.offsetY<=iconY+15))return{type:c[5].c||"",name:d,uid:n.u||"",point:{x:iconX,y:iconY},clickFea:{tileId:e.id,tile:e,fea:c}}}return q},Rx:function(){return/M040/i.test(navigator.userAgent)},Pf:function(a){for(var b=a.offsetLeft,c=a.offsetTop,a=a.offsetParent;a&& a!=this.map.La();)b+=a.offsetLeft,c+=a.offsetTop,a=a.offsetParent;return{top:c,left:b}},pC:function(a){if(this.map.Wb()){this.map.oa().k.$b=18;var a=this.Jt=a,b;for(b in a)switch(b){case "style":this.Op(a);break;case "styleStr":this.Op(a);break;case "features":this.SN(a[b]);break;case "poiElements":this.$N(a[b])}}},eE:function(){this.su();"dark"==this.Re?x.K.Ua(this.xb,"light_gray_background"):x.K.Ua(this.xb,"gray_background")},su:function(){x.K.Tb(this.xb,"gray_background");x.K.Tb(this.xb,"light_gray_background")}, dW:function(a,b,c,d){var e=this;c.Pm||(c.Pm=[],c.handle={});c.Pm.push({filter:d,rm:b});c.handle[a]||(c.addEventListener(a,function(a){for(var b=a.target;b!=c;){e.Gb(c.Pm,function(c,d){RegExp(d.filter).test(b.getAttribute("filter"))&&d.rm.call(b,a,b.getAttribute("filter"))});b=b.parentNode}},q),c.handle[a]=o)},Gb:function(a,b){for(var c=0,d=a.length;c<d;c++)b(c,a[c])}});window.VectorLayer=If;Jf=1;Kf=2;Lf=3;Mf=4;Nf=5; function Pf(){this.Lm=q;this.jt=[z.url.proto+z.url.domain.TILE_ONLINE_URLS[1]+"/"];this.UF=[z.url.proto+z.url.domain.TILE_ONLINE_URLS[0]+"/gvd/?",z.url.proto+z.url.domain.TILE_ONLINE_URLS[1]+"/gvd/?",z.url.proto+z.url.domain.TILE_ONLINE_URLS[2]+"/gvd/?",z.url.proto+z.url.domain.TILE_ONLINE_URLS[3]+"/gvd/?"];this.eb=p;this.pk={};this.map=p;this.Ye=this.jj=0;this.bi=p;this.Fb=window.Fb;this.tz=x.extend({},window.Fb);this.hm={dark:{backColor:"#2D2D2D",textColor:"#bfbfbf",iconUrl:"vector/dicons"},normal:{backColor:"#F3F1EC", textColor:"#c61b1b",iconUrl:"vector/nicons_hd"},light:{backColor:"#EBF8FC",textColor:"#017fb4",iconUrl:"vector/licons"}};this.Ps={};this.qp=o;this.nk=p;this.cm=/.*GT-I9300.*Version\\/\\d+.*Safari\\/\\d+\\.\\d+$/ig.test(navigator.userAgent)||/baiduboxapp/ig.test(navigator.userAgent)} Pf.prototype={Gs:function(a,b,c){this.kO=(new Date).getTime();var d=a.bg,e=a.poi;this.JF=d.length;this.jj=0;this.Ye=d.length;this.kK();this.Lm||(this.Lm=o,this.map=b.map,this.eb=b,this.Ac=this.map.C.devicePixelRatio,0<this.Ye&&(this.Qb=parseInt(d[0][2].style.width,10)));this.map.Bb();this.mO=0;this.WE=c;b=this.map.Oa;this.bn=Math.pow(2,18-b);this.Gj?this.Gj.length=0:this.Gj=[];this.map.dispatchEvent(new M("onvectorbegin"));if(this.JF<=e.length)var f=0,g=this.Ye;else f=0,g=e.length;for(;f<g;f++)d[f][2].$e= q,e[f][2].$e=q,d[f][2].uq=(new Date).getTime(),e[f][2].uq=(new Date).getTime(),this.ly(d[f][0],d[f][1],d[f][2],b,e[f][2]||p,c,a.isZoomMap)},kK:function(){for(var a in this.pk)delete this.pk[a]},YC:function(a,b,c,d,e){this.Gj?this.Gj.length=0:this.Gj=[];this.kO=(new Date).getTime();var d=this.map.Oa,f=this.map.Ha(),f=new H(f.lng,f.lat);this.WE=c;this.nk=e;for(var e=0,g=a.length;e<g;e++){b[e].uq=(new Date).getTime();var i=a[e][0],k=a[e][1],l="_"+parseInt(i+""+k+""+d).toString(36);"df"==c&&this.pk[l]? (i=this.pk[l],b[e].Zd=i,this.nj(b[e]),this.No(i,b[e],d,p,f,d)):this.ly(i,k,b[e],d,p,c)}},ly:function(a,b,c,d,e,f){var g=this,i=g.UF,k=Math.abs(parseInt(a,10)+parseInt(b,10))%i.length,l="x="+a+"&y="+b+"&z="+d,m=g.map.ba.replace(/^TANGRAM_/,""),n="undefined"!=typeof TVC?TVC.UJ.O4:{},t=n.version?n.version:"002",n=n.tu?n.tu:"20150601",v="",w=m+(0>a?"_":"")+(0>b?"$":"")+parseInt(Math.abs(a)+""+Math.abs(b)+""+d,10).toString(36);if(c&&e)if(g.bi)if("no"==g.bi)v="&layers=&features="+g.bi,c.Zd=p,e.Zd=p,this.cm? (f=c.getContext("2d"),a=e.getContext("2d"),f.canvas.width=f.canvas.width,a.canvas.width=a.canvas.width,a=f=p):(g.nj(c),g.nj(e)),g.Ye=0;else if(-1<g.bi.indexOf("pts"))if("pts,"==g.bi)c.Zd=p,this.cm?(v=c.getContext("2d"),v.canvas.width=v.canvas.width,v=p):g.nj(c),v="&layers="+f;else{g.Ye<2*g.JF&&(g.Ye*=2);a=g.bi.split(",");b="";v=0;for(m=a.length;v<m;v++)"pts"!=a[v]&&""!=a[v]&&(b=a[v]+","+b);v="&layers=bg,"+f+"&features="+b;b=a=p}else e.Zd=p,this.cm?(v=e.getContext("2d"),v.canvas.width=v.canvas.width, v=p):g.nj(e),v="&layers=bg&features="+g.bi;else v="&layers=bg,"+f;else v="&layers="+f;var i=(i[k]?i[0]:i[k])+"qt=lgvd&"+l+"&styles=pl"+v+"&f=mwebapp&v="+t+"&udt="+n+"&fn=BMap."+w,k=g.map.Ha(),y=new H(k.lng,k.lat),B=g.map.fa();z[w]=function(a){var b=a.content;if(b){c.rq=(new Date).getTime();e&&(e.rq=(new Date).getTime());var f=g.map,a=f.Ha(),f=f.fa();if(!a.nb(y)||f!=B){delete z[w];return}var a={},i;for(i in b)"df"==i&&(g.pk[w]=b[i]),a[i]=b[i];for(var k in a){i=a;for(var b=k,f=a[k],l=0,m=f.length;l< m;l++)for(var n=f[l][1],t=0,v=0,Ja=0,Oa=n.length/2;Ja<Oa;Ja++)t+=n[2*Ja]/10,v+=n[2*Ja+1]/10,n[2*Ja]=t,n[2*Ja+1]=v;i[b]=f;"bg"==k?c.tq=(new Date).getTime():e?e.tq=(new Date).getTime():c.tq=(new Date).getTime()}for(var Ra in a)"bg"==Ra?(c.Zd=a[Ra],c&&g.nj(c),g.No(a[Ra],c,d,p,y,B)):e!==p?(e.Zd=a[Ra],g.nj(e),g.No(a[Ra],e,d,p,y,B)):(c.Zd=a[Ra],c&&g.nj(c),g.No(a[Ra],c,d,p,y,B))}delete z[w]};oa(i)},nj:function(a){var a=a.getContext("2d"),b=this.Qb*this.Ac;this.cm||(a.save(),a.clearRect(0,0,b,b),a.restore())}, WC:function(a,b,c){a.fillStyle=c;a.fillRect(0,0,b,b)},No:function(a,b,c,d,e,f){b.aV=(new Date).getTime();var g=b.getContext("2d"),i=0;this.cm?(g.canvas.width=g.canvas.width,g.scale(this.Ac,this.Ac)):1<this.Ac&&!b.kg&&(g.scale(this.Ac,this.Ac),b.kg=o);g.textBaseline="bottom";-1<b.id.indexOf("bg")&&this.map.C.Co&&this.WC(g,this.Qb,this.map.C.Co);for(var k=this.qp,l=a.length,d=0,m=this.Fb;d<l;d++){var n=a[d],t=m[n[3]]||window.Fb[n[3]],v=m[n[4]]||window.Fb[n[4]];n.uc=t;n.Pc=v;if(t[0]==Lf)i++,this.om(g, n,p,f);else break}b.$U=(new Date).getTime();n=this.map.Ha();c=this.map.fa();if(n.nb(e)&&c==f){b.BZ=(new Date).getTime();for(var w=[];d<l;d++){var n=a[d],t=m[n[3]]||window.Fb[n[3]],v=m[n[4]]||window.Fb[n[4]];n.uc=t;n.Pc=v;17<=c&&(t[5]&&0<t[5].length&&1==t[5][0]&&6==t[5][1]&&v&&0<v.length)&&(t[5].length=0,t[6]=0,v[6]=0);if(t[0]==Kf)i++,w.push(n);else break}this.hF(g,w,c,this.bn);b.AZ=(new Date).getTime();n=this.map.Ha();c=this.map.fa();if(n.nb(e)&&c==f){for(b.aZ=(new Date).getTime();d<l;d++)n=a[d], t=m[n[3]]||window.Fb[n[3]],v=m[n[4]]||window.Fb[n[4]],n.uc=t,n.Pc=v,n[5]&&n[5].u&&this.nk&&n[5].c==this.nk.fea[5].c?("df"!==this.nk.fea[5].c?this.om(g,n,this.bn,o,f):n[5].u==this.nk.fea[5].u?this.om(g,n,this.bn,o,f):this.om(g,n,this.bn,q,f),n[5].u==this.nk.fea[5].u&&(c=new M("onclickicondrawed"),c.tarPoi={id:b.id,fea:n,equal:this.IV(n[1],this.nk.fea[1])},this.map.dispatchEvent(c))):this.om(g,n,this.bn,q,f),i++;b.$e=o;this.tZ();a=(new Date).getTime();b.$Y=a;b.FG=a;a=b.rq-b.uq;f=b.tq-b.rq;__drawTime= b.FG-b.tq;this.Gj.push({id:b.id,downLoadTime:a,parseDataTime:f,drawTime:__drawTime,restRate:i+"/"+d,areaTime:b.$U-b.aV,roadTime:b.AZ-b.BZ,otherTime:b.$Y-b.aZ,timeline:{start:b.uq,downLoadComplete:b.rq,parseComplete:b.tq,drawComplete:b.FG}});this.mO++;1==this.mO&&this.map.dispatchEvent(new M("onfirstvectorloaded"));if(this.Ye==this.jj){if(k){this.qp=q;for(d=b=0;i=this.Gj[d];d++)b+=i.drawTime;this.map.Bb();z.En("cus.fire","time",{z_vectorfirstdrawtime:b})}d=(new Date).getTime()-this.kO;c=new M("onvectorloaded"); c.JF=this.Ye;c.y4=d;c.t4=this.Gj;this.map.dispatchEvent(c);this.map.dispatchEvent(new M("ontilesloaded"))}}}},tZ:function(){this.jj++;2>=this.Ye-this.jj&&this.map.dispatchEvent(new M("onallvectorloaded"))},IV:function(a,b){var c=q;if(a.length&&b.length&&a.length==b.length){for(var d=0,e=a.length;d<e&&a[d]===b[d];d++);d==e&&(c=o)}return c},om:function(a,b,c,d,e){switch(b.uc[0]){case Lf:this.bx(a,b,e);break;case Kf:this.se(a,b[1],b.uc,b.Pc,b[2],c);break;case Mf:this.VC(a,b);break;default:this.XC(a, b,d)}},bx:function(a,b,c){var d=b.uc,e=d[2],b=b[1];a.fillStyle=z.Db.ue(d[1]);a.beginPath();a.moveTo(b[0],b[1]);for(var d=2,f=b.length;d<f;d+=2)a.lineTo(b[d],b[d+1]);a.closePath();a.fill();0<e.length&&(a.strokeStyle=a.fillStyle,a.lineWidth=12<=c?3:e[3],a.stroke())},se:function(a,b,c,d,e,f){if(c||d){var g=z.Db.ue,i=z.Db.qx,k=z.Db.sx;if(this.ot(c,d))firstColor=backColor=(g=d&&d[5]&&0<d[5].length?o:q)?z.Db.ue(c[1]):"rgba(0, 0, 0, 0)",backLineWidth=c[2],foreLineWidth=g?d[2]:c[2],intervalLen=g?d[5][0]: c[5][0],intervalColor=z.Db.ue(g?d[1]:c[1]),c=Math.round(e/f),z.Db.uW(a,b,intervalLen,backLineWidth,foreLineWidth,firstColor,c,backColor,intervalColor);else if(1==c[7])a.strokeStyle=g(c[1]),a.fillStyle=a.strokeStyle,a.lineWidth=c[2],a.lineCap=i(c[3]),a.lineJoin=k(c[4]),z.Db.NK(a,b,a.lineWidth);else{a.beginPath();a.moveTo(b[0],b[1]);e=2;for(f=b.length;e<f;e+=2)a.lineTo(b[e],b[e+1]);a.strokeStyle=g(c[1]);a.lineCap=i(c[3]);a.lineJoin=k(c[4]);a.lineWidth=c[2];a.stroke();d&&(a.strokeStyle=g(d[1]),a.lineWidth= d[2],a.lineCap=i(d[3]),a.lineJoin=k(d[4]),a.stroke())}}},VC:function(a,b){var c=b[1],d=b.uc,e=z.Db.ue,f=e(d[1]),g=e(d[2]),i=d[4],e=e(i[1]),i=i[2],d=d[5];z.Db.ZC(a,c,0,d,0,f,g,e,i)},XC:function(a,b,c){a.save();var d=b[1],e=b[0],f=b[2],b=this.Xs(b.uc,b.Pc),g=b[1],i=0;if(!("undefined"==typeof g||"number"==typeof g)){var k=-1<g.indexOf("biaopai");g.indexOf("ditie");if(0<g.length){var l="undefined"!=typeof TVC?TVC.UJ.g0:{},l=this.jt[g.length%this.jt.length]+this.hm.normal.iconUrl+"/"+g+".png?v="+(l.version? l.version:"002")+"&udt="+(l.tu?l.tu:"201500601"),m=new Image,n=d[0],t=d[1];2<d.length&&(i+=2);var v=this;if(k)(function(a,b,c,d,e,f,g,i,k,l,n){m.onload=function(){c.drawImage(this,a-this.width/4,b-this.height/4,this.width/2,this.height/2);v.Hs(c,d,e,f,g,i,k,l,n);m.onload=p;delete m.onload;m=p}})(n,t,a,d,b,f,e,g,i,k,c),m.src=l;else{var w=v.Ps[g];w?a.drawImage(w,n-w.width/4,t-w.height/4,w.width/2,w.height/2):(function(b,c,d){m.onload=function(){a.drawImage(this,b-this.width/4,c-this.height/4,this.width/ 2,this.height/2);d&&(v.Ps[g]=m);m.onload=p;delete m.onload;m=p}}(n,t,v.qp),m.src=l)}}!k&&(b[2]&&0<b[2].length)&&this.Hs(a,d,b,f,e,g,i,k,c)}a.restore()},Hs:function(a,b,c,d,e,f,g,i,k){var l=z.Db.ue,m=c[2];if(e&&0<m.length){var n=[],c=m[2],t=m[3],v=m[4],m=m[5];n.push(z.Db.wL(v));n.push(c+"px");i||n.push("Helvetica Neue,Arial,Hiragino Sans GB,\\u9ed1\\u4f53,sans-serif");a.font=n.join(" ");a.fillStyle=k?"#c61b1b":l(t);if(k=z.Db.gM(v))a.strokeStyle=l(m),a.lineWidth=i?0.5:2;for(var f=-1<f.indexOf("biaopai_xiandao"), n=e.split("\\\\"),t=0,w=n.length,y=b.length;t<w&&g<y;t++){var B=b[g],A=b[g+1],e=n[t],e=a.measureText(e).width,E=c,g=g+2;10<d&&350>d&&this.SF(a,B,A,d);var C=1;z.Db.fM(v)&&(z.Db.bL(a,B-e/2,A-E/2,e,E,{fillStyle:l(m)}),C=0);f?(a.save(),a.scale(0.9,0.9),k&&a.strokeText(n[t],(B-e/2+1)/0.9,(A+E/2+1)/0.9),a.fillText(n[t],(B-e/2+1)/0.9,(A+E/2+1)/0.9),a.restore()):(C=i?2:C,k&&a.strokeText(n[t],B-e/2,A+E/2+C),a.fillText(n[t],B-e/2,A+E/2+C))}}},Xs:function(a,b){var c=[Jf,"",[]];a&&(a[0]==Nf?c[2]=a:c=a);b&&(b[0]== Nf?c[2]=b:c[1]=b[1]);return c},SF:function(a,b,c,d){d=d/180*Math.PI;cv=Math.cos(d);sv=Math.sin(d);yy=xx=cv;xy=sv;yx=-sv;x0=b-b*cv-c*sv;y0=c+b*sv-c*cv;a.transform(xx,yx,xy,yy,x0,y0)},hF:function(a,b,c,d){if(16>=c)for(var c=0,e=b.length;c<e;){for(var f=b[c],g=this.at(f.uc,f.Pc),f=c+1;f<e;f++){var i=b[f];if(g!=this.at(i.uc,i.Pc))break}for(var k=c;k<f;k++){var l=b[k],m=l[1],g=l.uc,i=l.Pc;this.ot(g,i)?l.nE=o:this.se(a,m,g,q)}for(k=c;k<f;k++)l=b[k],l.nE?this.se(a,l[1],l.uc,l.Pc,l[2],d):this.se(a,l[1],l.Pc, q);c=f}else{c=0;for(e=b.length;c<e;c++)f=b[c],g=f.uc,i=f.Pc,k=g[6]&1?o:q,i&&!k&&(k=i[6]&1?o:q),k?f.wE=o:this.se(a,f[1],g,q);c=0;for(e=b.length;c<e;c++)f=b[c],g=f.uc,i=f.Pc,f.wE?this.se(a,f[1],g,i,f[2],d):this.se(a,f[1],i,q)}},at:function(a,b){if(!b)return 0;var c=a[6],d=b[6];if(1==c||1==d)return 1;switch(c){case 2:return 2==d?1:0;case 4:case 6:case 8:case 10:return 4<=d&&10>=d?1:0;default:return 0}},ot:function(a,b){return a&&0<a.length&&0<a[5].length||b&&0<b.length&&0<b[5].length?o:q}}; z.VectorDrawLib=Pf; ');
