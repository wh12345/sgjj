/**/_jsload2&&_jsload2('drawbysvg', 'function vg(a){this.D=a;this.AG="http://www.w3.org/2000/svg";this.zU={strokeweight:"stroke-width",strokecolor:"stroke",fillcolor:"fill",strokeopacity:"stroke-opacity",fillopacity:"fill-opacity",strokestyle:"stroke-dasharray"};this.gf=this.Qz();this.Nb="svg"}vg.prototype=new z.$y;var wg=vg.prototype; wg.Qz=function(){var a=this.gf=K("svg",{},this.AG);a.setAttribute("version","1.1");a.setAttribute("type","system");a.style.position="absolute";this.xF();this.D.Qf().At.appendChild(a);x.M(a,G()?"touchstart":"mousedown",zb);return a}; wg.xF=function(){if(this.gf){var a=this.gf,b=this.D,c=b.C.ax,d=b.width+2*c,e=b.height+2*c,f=-b.offsetX-c,b=-b.offsetY-c;this.setAttribute(a,"x",d+"px");this.setAttribute(a,"y",e+"px");this.setAttribute(a,"viewBox",f+" "+b+" "+d+" "+e);a=a.style;a.top=b+"px";a.left=f+"px";a.width=d+"px";a.height=e+"px"}};wg.Q2=u("gf");wg.setAttribute=function(a,b,c,d){if(a)return"strokestyle"==b&&(c="solid"==c?0:2*d),a.setAttributeNS(p,this.Xq(b),c||"none"),a};wg.Xq=function(a){return this.zU[a]||a}; wg.zo=function(){var a=K("path",{},this.AG);this.setAttribute(a,"stroke-linejoin","round");this.setAttribute(a,"stroke-linecap","round");this.setAttribute(a,"fill-rule","evenodd");this.gf.appendChild(a);return a};wg.ke=function(a,b){var c=this.kA(b)||"M -9999,-9999";this.setAttribute(a,"d",c)};wg.kA=function(a){if(0==a.length)return"";var b=[];x.kc.Gb(a,function(a){if(!(2>a.length)){b.push("M "+a[0].x+" "+a[0].y+" L");for(var d=1,e=a.length;d<e;d++)b.push(a[d].x),b.push(a[d].y)}});return b.join(" ")}; z.HP=vg; ');
