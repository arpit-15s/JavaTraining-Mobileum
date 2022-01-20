package in.srivastavarpit.app;

import in.srivastavarpit.business.furnitures.Table;
import in.srivastavarpit.business.furnitures.Chair;
import in.srivastavarpit.business.services.StoreManager;
import in.srivastavarpit.utils.data.List;
import in.srivastavarpit.utils.data.QuickSort;
import in.srivastavarpit.utils.presentation.Input;
import in.srivastavarpit.utils.presentation.Printer;


class App {

    public static void main(String []args){

        StoreManager manager=new StoreManager();
        
        Chair chair =new Chair();
        Table table1=new Table();


        in.srivastavarpit.utils.data.Table table2=new in.srivastavarpit.utils.data.Table();

        List list=new List();
        
        Input input =new Input();
        
        Printer printer=new Printer();

        QuickSort sort=new QuickSort();

        System.out.println(table1);
        System.out.println(table1.getPrice());

        System.out.println(table2);
        System.out.println(table2.getColumnCount());


        System.out.println(manager);
        System.out.println(chair);
        System.out.println(list);
        System.out.println(input);
        System.out.println(printer);
        System.out.println(sort);

    }
    
}
