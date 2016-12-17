import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.*;

public class acofortsp
    {
    static public int Ants = 50; // count of ants;
    static public int Iterations = 2000;
    static public int Verticles = 7;
    static public int MaxLength = 9;// max length of path
    static public double Alpha = 1; // wykladnik
    static public double Beta = 1; // wykladnik
    static public double Ro = 0.01; // p in (1-p)*pheromone
    static public double Q = 4; // adding pheromone
    static public double Bonus = 1; // prize for finding better way
    static public List<List<Path>> graph;
    static MainFrame frame;
    static public List<Path> bestway;
    
	public static void main(String[] args)
        {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
			//start();
        }
	
	public static void start()
	{
		graph = GenerateGraph();
        List<Ant> ants = new ArrayList<Ant>();
        List<Path> bbb = new ArrayList<Path>();
        List<Integer> results = new ArrayList<Integer>();
		results.add(MaxLength * Verticles);
        List<Ant> finished = new ArrayList<Ant>();
		int shortest = 10000;
		
        Random r = new Random(105);
        for (int j = 0; j < Ants; j++) ants.add(new Ant(r.nextInt(Verticles), Verticles));
        double n = 0.0;
        for (int i = 0; i < Iterations; ++i) 
        {
			for(int index=0;index < ants.size();++index)
            {
                if (ants.get(index).visits == Verticles) // all cities are visited
                {
                    ants.get(index).lengthOfWay += graph.get(ants.get(index).start_city).get(ants.get(index).way.get(ants.get(index).way.size()-1).to).Length;
                    ants.get(index).way.add(graph.get(ants.get(index).way.get(ants.get(index).way.size()-1).to).get(ants.get(index).start_city));
                    int res = ants.get(index).lengthOfWay;
					if(res < shortest) 
						{
						shortest = res; bestway = ants.get(index).way;
						}
					results.add(res);
                    double delta = Q / ants.get(index).lengthOfWay;
					for(int another_index = 0; another_index < ants.get(index).way.size();++another_index)
					{
						ants.get(index).way.get(another_index).pheromone += Bonus * delta; 
					}
					for(int another_index = 0; another_index < graph.size();++another_index)
					{
						for(int one_more_another_index = 0; one_more_another_index < graph.get(another_index).size() ; one_more_another_index++)
						{
							graph.get(another_index).get(one_more_another_index).pheromone *= (1-Ro);
						}
					}
					// TUTAJ MOZNA ODRYSOWAC CALY GRID
					
					//System.out.println("# " + frame.p.getGraphics());
					Boolean next = true;
					while (next == true){
						try {
							frame.p.getGraphics();
							if (frame.p.getGraphics() != null) next = false;
						}catch(Exception e){
							//System.out.println("");
							next = true;
						}
					}
					Draw(graph, ants, bestway, Verticles); // ArrayList<List<Path>> ; ArrayList<Ant> ; int
					//Draw(graph, ants, bestway, Verticles); // ArrayList<List<Path>> ; ArrayList<Ant> ; ArrayList<Path>  ;int
					ants.get(index).Clear();
                }
				n = 0.0;
				
				for(int another_index=0;another_index < graph.get(ants.get(index).position).size()-1 ; another_index++)
				{
					if(!ants.get(index).cities.get(
							graph.get(ants.get(index).position).get(another_index).to))
					{
						n += graph.get(ants.get(index).position).get(another_index).GetMultiplier();
					}
				}
				for(int another_index=0;another_index < graph.get(ants.get(index).position).size() ; another_index++)
				{	
					if(!ants.get(index).cities.get(graph.get(ants.get(index).position).get(another_index).to))
					{
						if (r.nextDouble() > graph.get(ants.get(index).position).get(another_index).GetMultiplier() / n) continue;
						ants.get(index).Move(graph.get(ants.get(index).position).get(another_index));
						ants.get(index).cities.set(graph.get(ants.get(index).position).get(another_index).to,true);
						ants.get(index).visits++;
						break;
					}
				}  
            }
        }
        frame.p.paintBestWay(frame.p.getGraphics(),bestway);
		int min = 100;
		for(int i=0;i<results.size();++i)
		{
			min = (results.get(i) < min) ? results.get(i) : min;
		}
		//System.out.println(min);
		
	}
	
	public static List<List<Path>> GenerateGraph()
        {
            Random r = new Random();
            List<List<Path>> res = new ArrayList<List<Path>>();
            for (int i = 0; i < Verticles; ++i)
            {
                res.add(new ArrayList<Path>());
                for (int j = 0; j < Verticles; ++j) 
                {
                    res.get(i).add(new Path(-1, -1, -1, -1));
                }
            }
            for (int i = 0; i < Verticles; ++i)
            {
                for (int j = i; j < Verticles; ++j)
                {
                    res.get(i).set(j,new Path(i, j, r.nextInt(MaxLength)+1, 1.0 / Verticles));
                }
            }
            for (int i = 0; i < Verticles; ++i)
            {
                for (int j = 0; j < i; ++j)
                {
                    res.get(i).set(j, new Path(i, j, res.get(j).get(i).Length, res.get(j).get(i).pheromone));
                }
            }
            return res;
        }
	
	public static void Draw(List<List<Path>> graph2, List<Ant> ants2, List<Path> bestway, int Verticles){
		
		frame.p.paintBackbone(frame.p.getBetterGraphics(),Verticles, bestway, graph2, ants2);
	}
	
	}