/**/_jsload2&&_jsload2('copyrightctrl', 'x.extend(Xb.prototype,{wf:function(){this.D&&this.Ge(this.D)},initialize:function(a){Sb.prototype.initialize.call(this,a);this.Aa();this.so();this.ca(a);return this.B},ca:function(a){var b=this;a.addEventListener("load",function(){b.so()});a.addEventListener("moveend",function(){b.so()});a.addEventListener("zoomend",function(){b.so()});a.addEventListener("maptypechange",function(){b.B&&(b.B.style.color=b.D.oa().Cm())})},Aa:function(){Sb.prototype.Aa.call(this);x.K.Ua(this.B,"BMap_cpyCtrl");var a= this.B.style;a.cursor="default";a.whiteSpace="nowrap";a.MozUserSelect="none";a.color=this.D.oa().Cm();a.background="none";a.font="11px/15px "+F.fontFamily;Sb.prototype.Or.call(this)},so:function(){if(this.D&&this.B&&0!=this.dc.length)for(var a=0,b=this.dc.length;a<b;a++){this.D.fa();var c=this.D.wb({x:0,y:0}),d=this.D.wb({x:this.D.width,y:this.D.height}),c=new db(c,d);if(this.dc[a].bounds&&c.mt(this.dc[a].bounds)==p){if(this.B)for(d=0;d<this.B.children.length;d++)if(this.B.children[d].getAttribute("_cid")== this.dc[a].id&&"none"!=this.B.children[d].style.display){this.B.children[d].style.display="none";return}}else if(this.B){for(var c=q,d=0,e=this.B.children.length;d<e;d++)if(this.B.children[d].getAttribute("_cid")==this.dc[a].id){c=o;this.B.children[d].style.display="inline";this.B.children[d].innerHTML!=this.dc[a].content&&(this.B.children[d].innerHTML=this.dc[a].content);break}c||this.Vq(this.dc[a])}}},sw:function(a){if(a&&Wa(a.id)&&!isNaN(a.id)){var b={bounds:p,content:""},c;for(c in a)b[c]=a[c]; if(a=this.tm(a.id))for(var d in b)a[d]=b[d];else this.dc.push(b);this.so()}},tm:function(a){for(var b=0,c=this.dc.length;b<c;b++)if(this.dc[b].id==a)return this.dc[b]},HD:u("dc"),dF:function(a){for(var b,c=0,d=this.dc.length;c<d;c++)this.dc[c].id==a&&(b=this.dc.splice(c,1),c--,d=this.dc.length);(a=this.Id(a))&&a.parentNode&&a.parentNode.removeChild(a);this.so();return b},Vq:function(a){this.B&&(this.B.innerHTML+="<span _cid=\'"+a.id+"\'>"+a.content+"</span>")},Id:function(a){var b=Sb.prototype.Id.call(this); if(Db(a)){if(b)for(var c=0,d=b.children.length;c<d;c++)if(b.children[c].getAttribute("_cid")==a)return b.children[c]}else return b}});T(lf,{addCopyright:lf.sw,removeCopyright:lf.dF,getCopyright:lf.tm,getCopyrightCollection:lf.HD}); ');