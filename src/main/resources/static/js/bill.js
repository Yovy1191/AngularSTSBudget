$(document).ready(function(){
	var i=1;
    $('#ItemId').val(i);
    $("#add_row").click(function(){b=i-1;
      	$('#addr'+i).html($('#addr'+b).html()).find('td:first-child').html(i+1);
      	$('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
      	i++; 
  	});
    $("#delete_row").click(function(){
    	if(i>1){
		$("#addr"+(i-1)).html('');
		i--;
		}
		calc();
		calcTax();
	});
	
	$('#tab_logic tbody').on('keyup change',function(){
		calc();
		calcTax();
	});
	$('#tps').on('keyup change',function(){
		calcTax();
		calc_total();
	});
	$('#tvq').on('keyup change',function(){
		calcTax();
		calc_total();
	});



	
});

function calc()
{    
	
	$('#tab_logic tbody tr').each(function(i, element) {
		var html = $(this).html();
		if(html!='')
		{
			var qty = $(this).find('.qte').val();
			console.log(qty);
			var price = $(this).find('.price').val();
			console.log(price);
			$(this).find('.total').val(qty*price);
			var total= $(this).find('.total').val();
			console.log(total);
			calcTax();
			calc_total();
		}
    });
}

function calcTax()
{    
	
	 
	$('#tab_logic tbody tr').each(function(i, element) {
		var html = $(this).html();
		if(html!='')
		{
			var subtotal = 0 ;
			var x = document.getElementById("taxesIncluded").checked;
			$('.total').each(function() {
				subtotal += parseInt($(this).val());
		    });
			console.log(x);
			if (!x)  {
						 $('#tvq').val((subtotal*9.975/100).toFixed(2));
						 $('#tps').val((subtotal*5/100).toFixed(2));
					     
					    }
					 else
					 {
				    $('#tvq').val((subtotal*0/100).toFixed(2));
					$('#tps').val((subtotal*0/100).toFixed(2));
					 }
		calc_total();
		}
    });
}

function calcTpsTpq()
{    
	
		
 
}


function calc_total()
{
	total=0;
	$('.total').each(function() {
        total += parseInt($(this).val());
    });
	$('#sub_total').val(total.toFixed(2));
	tax_sum=(parseFloat($('#tps').val())+parseFloat($('#tvq').val())).toFixed(2);
	$('#tax_amount').val(tax_sum);
	var total_am = (parseFloat($('#tax_amount').val()) + parseFloat($('#sub_total').val()));
	console.log(total_am);
	$('#total_amount').val((total_am).toFixed(2));
}


