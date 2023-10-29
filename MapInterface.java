
public interface MapInterface {
	
	

	default public void print() {
		System.out.println(status());
		
	}

	// boş harita hali. bu metot güncellenmeli
	default public String status() {
		String s = "";
		for(int i=0;i<50;i++)
		{
			for(int j=0;j<100;j++)
				s +="_";
			s += "\n";
		}
		return s;
		
	}
	
}
