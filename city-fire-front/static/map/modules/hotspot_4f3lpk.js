/**/_jsload2&&_jsload2('hotspot', 'z.We(function(a){function b(a){var b=this.R;b.Qe=[];if(this.Oh)for(var c in this.Oh)if(!(0==c.indexOf("vector_")&&!this.Wb()||0==c.indexOf("tile_")&&this.Wb())){var g=this.Oh[c],i;for(i in g){var k=g[i];if(!(k.Qh>this.fa()||k.Ef<this.fa())){var l=this.bc(k.ha());l.x<this.width&&l.y<this.height&&a.offsetX<l.x+k.Bv[1]&&(a.offsetX>l.x-k.Bv[3]&&a.offsetY>l.y-k.Bv[0]&&a.offsetY<l.y+k.Bv[2])&&b.Qe.push(k)}}}}function c(){a.R.KK&&0<a.R.Qe.length?(a.platform.style.cursor="pointer",a.R.KK=q):(a.R.Qe=[],a.platform.style.cursor= a.C.Yb)}a.R.gu=new tc("",{za:new L(15,-3),Xp:{border:"1px solid #aaa",background:"#fffec2",whiteSpace:"nowrap",font:"12px "+F.fontFamily,mozBoxShadow:"1px 2px 6px #666",webkitBoxShadow:"1px 2px 6px #666",boxShadow:"1px 2px 6px #666",padding:"2px 4px"}});a.addEventListener("mousemove",function(c){if(!(c.bb&&!(c.bb instanceof lc)||c.Cb)){var e=this.R,f=e.Qe.slice(0);b.call(this,c);for(var g=e.Qe.slice(0),i=0;i<f.length;i++)for(var k=0;k<g.length;k++)f[i]===g[k]&&(f.splice(i,1),i--,g.splice(k,1),k--); if(0<f.length){var l=new M("onhotspotout");l.spots=f.slice(0);this.dispatchEvent(l)}if(0==e.Qe.length&&!e.Sx)this.platform.style.cursor!=this.C.Yb&&(this.platform.style.cursor=this.C.Yb),e.Wp&&(clearTimeout(e.Wp),e.Wp=p),e.FF=setTimeout(function(){e.gu.U()},400);else if(0<g.length&&(this.platform.style.cursor="pointer",l=new M("onhotspotover"),l.spots=g.slice(0),this.dispatchEvent(l),e.Wp&&(clearTimeout(e.Wp),e.Wp=p),e.FF&&(clearTimeout(e.FF),e.FF=p),l.spots[0].YD()))e.Wp=setTimeout(function(){e.gu.ad(l.spots[0].YD()); e.gu.ta(c.point);e.gu.show();a.Ka(e.gu)},400)}});a.addEventListener("clickex",function(a){var c=this.R;a.overlay||(0==c.Qe.length&&b.call(this,a),0<c.Qe.length&&(a=new M("onhotspotclick"),a.spots=c.Qe.slice(0),a.spots.sort(function(a,b){return a.ha().lat-b.ha().lat}),this.dispatchEvent(a),c.Qe.length=0))});a.addEventListener("load",c);a.addEventListener("moveend",c);a.addEventListener("zoomend",c);a.addEventListener("dragend",function(){this.R.KK=o})}); ');
