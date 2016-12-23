var TINY={};

function T$(i){return document.getElementById(i)}
function T$$(e,p){return p.getElementsByTagName(e)}

TINY.table=function(){
	function sorter(n){this.n=n; this.pagesize=100; this.paginate=0}
	sorter.prototype.init=function(e,f){
		var t=ge(e), i=0; this.e=e; this.l=t.r.length; t.a=[];
		t.h=T$$('thead',T$(e))[0].rows[0]; t.w=t.h.cells.length;
		for(i;i<t.w;i++){
			var c=t.h.cells[i];
			if(c.className!='nosort'){
				c.className=this.head; c.onclick=new Function(this.n+'.wk(this.cellIndex)')
			}
		}
		for(i=0;i<this.l;i++){t.a[i]={}}
		if(f!=null){var a=new Function(this.n+'.wk('+f+')'); a()}
		if(this.paginate){this.g=1; this.pages()}
	};
	//xiu gai hou bu pai xu
	sorter.prototype.wk=function(y){
		 if(this.paginate){this.size(this.pagesize)}
	};
	sorter.prototype.page=function(s){
		var t=ge(this.e), i=0, l=s+parseInt(this.pagesize);
		if(this.currentid&&this.limitid){T$(this.currentid).innerHTML=this.g}
		for(i;i<this.l;i++){t.r[i].style.display=i>=s&&i<l?'':'none'}
	};
	sorter.prototype.move=function(d,m){
		var s=d==1?(m?this.d:this.g+1):(m?1:this.g-1);
		if(s<=this.d&&s>0){this.g=s; this.page((s-1)*this.pagesize)}
	};
	sorter.prototype.size=function(s){
		this.pagesize=s; this.g=1; this.pages(); this.page(0);
		if(this.currentid&&this.limitid){T$(this.limitid).innerHTML=this.d}
	};
	sorter.prototype.pages=function(){this.d=Math.ceil(this.l/this.pagesize)};
	function ge(e){var t=T$(e); t.b=T$$('tbody',t)[0]; t.r=t.b.rows; return t};
	function cp(f,c){
		var g,h; f=g=f.v.toLowerCase(), c=h=c.v.toLowerCase();
		var i=parseFloat(f.replace(/(\$|\,)/g,'')), n=parseFloat(c.replace(/(\$|\,)/g,''));
		if(!isNaN(i)&&!isNaN(n)){g=i,h=n}
		i=Date.parse(f); n=Date.parse(c);
		if(!isNaN(i)&&!isNaN(n)){g=i; h=n}
		return g>h?1:(g<h?-1:0)
	};
	return{sorter:sorter}
}();