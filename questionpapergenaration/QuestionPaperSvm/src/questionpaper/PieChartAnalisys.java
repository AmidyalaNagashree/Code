package questionpaper;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class PieChartAnalisys extends ApplicationFrame {
   public  int totalCountries =0;
   public String movies = "";
   public double acc=0.0;
   public double pre=0.0;
   public double rec=0.0;
   
   long DataFecthTime = 0;
   long ProcessingNLPSVN = 0;
   String titleMain = "";
   
   public PieChartAnalisys(String title,long nonMixedCountF,long mixedCountF){
       super(title);
       DataFecthTime = nonMixedCountF;
       ProcessingNLPSVN = mixedCountF;
       titleMain = title;
       setContentPane(createDemoPanel(DataFecthTime, ProcessingNLPSVN));
   }
   
   public PieChartAnalisys(String title,int nonMixedCountF,int mixedCountF){
       super(title);
       DataFecthTime = nonMixedCountF;
       ProcessingNLPSVN = mixedCountF;
       titleMain = title;
       setContentPane(createDemoPanel(DataFecthTime, ProcessingNLPSVN));
   }
   
   public PieChartAnalisys( String title ,int totalNoOfCountries,String movie, double accuracy,double precision,double recall) {
       
      super( title ); 
      totalCountries = totalNoOfCountries;
      movies = movie;
      acc=accuracy;
      pre=precision;
      rec=recall;
      setContentPane(createDemoPanel( ));
   }
   
   private  PieDataset createDataset(int nonMixC , int mixC) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "DataFecthTime ",nonMixC);  
      dataset.setValue( "ProcessingNLPSVN ",mixC );  
      
      
      return dataset;         
   }
   
    private  PieDataset createDataset(long nonMixC , long mixC) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "DataFecthTime ",nonMixC);  
      dataset.setValue( "ProcessingNLPSVN ",mixC );  
      
      return dataset;         
   }
   
   
   private  PieDataset createDataset(int contries , String mov,double acc1,double pre1,double rec1) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "No Of tweets P/N "+contries , contries );  
      dataset.setValue( "Movie is "+mov ,new Double("1") );  
      dataset.setValue( "Accuracy of "+acc1 ,acc1 );
      dataset.setValue( "Precisions of "+pre1 ,pre1 );
      dataset.setValue( "Recall of "+rec1 ,rec1 );
      
      return dataset;         
   }
   
   private  JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Question Paper Genaration ",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public JPanel createDemoPanel( long nMCount,long mCount) {
      JFreeChart chart = createChart(createDataset( nMCount,mCount) );  
      return new ChartPanel( chart ); 
   }
   
   public JPanel createDemoPanel( int nMCount,int mCount) {
      JFreeChart chart = createChart(createDataset( nMCount,mCount) );  
      return new ChartPanel( chart ); 
   }
   public JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset( totalCountries, movies,acc,pre,rec) );  
      return new ChartPanel( chart ); 
   }

   public static void main( String[ ] args ) {
//      PieChartCountry demo = new PieChartCountry( "Segementing customers" ,34,900);  
//      demo.setSize( 560 , 367 );    
//      RefineryUtilities.centerFrameOnScreen( demo );    
//      demo.setVisible( true ); 
   }
}