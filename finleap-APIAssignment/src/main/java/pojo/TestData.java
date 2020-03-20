package pojo;

public class TestData {


    private Trilogy trilogy;

 

    public Trilogy getTrilogy ()
    {
        return trilogy;
    }

    public void setTrilogy (Trilogy trilogy)
    {
        this.trilogy = trilogy;
    }

  
    
    
    public class Trilogy
    {
        private String hobitTrilogy;

        private String LotR_trilogy;

        public String getHobitTrilogy ()
        {
            return hobitTrilogy;
        }

        public void setHobitTrilogy (String hobitTrilogy)
        {
            this.hobitTrilogy = hobitTrilogy;
        }

        public String getLotR_trilogy ()
        {
            return LotR_trilogy;
        }

        public void setLotR_trilogy (String LotR_trilogy)
        {
            this.LotR_trilogy = LotR_trilogy;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [hobitTrilogy = "+hobitTrilogy+", LotR_trilogy = "+LotR_trilogy+"]";
        }
    }
    	
}
