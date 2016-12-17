import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.*;

class Ant
    {
        public int position;
        public int lengthOfWay;
        public List<Path> way; // list of visited verticles
        public List<Boolean> cities;
        public int visits;
        public int start_city;
        public Ant(int pos)
        {
            this.position = pos;
            this.start_city = pos;
            this.way = new ArrayList<Path>();
            this.visits = 1;
            this.lengthOfWay = 0;
            this.cities = new ArrayList<Boolean>();
            for (int i = 0; i < acofortsp.Verticles; ++i) this.cities.add(false);
            this.cities.set(position,true);
        }
        public void Move(Path p)
        {
            this.position = p.to;
            this.lengthOfWay += p.Length;
            this.way.add(p);
        }
        public void Clear()
        {
            this.position = this.start_city;
            this.visits = 1;
            this.way = new ArrayList<Path>();
            this.lengthOfWay = 0;
            for (int i = 0; i < acofortsp.Verticles; ++i) this.cities.set(i,false);
            this.cities.set(position,true);
        }
    }
class Path
    {
        public int from;
        public int to;
        public double pheromone;
        private int _length;
        public double InvertedLength;
        public int Length;
        public Path(int s, int e, int l,double ph)
        {
            this.from = s;
            this.to = e;
            this.pheromone = ph;
            this.Length = l;
			this.InvertedLength = 1.0 / this.Length; // bugogenne, wcześniej był setter zmieniający dwa!
        }
        public double GetMultiplier()
        {
            return Math.pow(this.pheromone, acofortsp.Alpha) *
                   Math.pow(this.InvertedLength, acofortsp.Beta);
        }
    }
public class acofortsp
    {
    static public int Ants = 20; // count of ants;
    static public int Iterations = 500;
    static public int Verticles = 10;
    static public int MaxLength = 9;// max length of path
    static public double Alpha = 1; // wykladnik
    static public double Beta = 1; // wykladnik
    static public double Ro = 0.01; // p in (1-p)*pheromone
    static public double Q = 2; // adding pheromone
    static public double Bonus = 1; // prize for finding better way
    static public List<List<Path>> graph;
    static MainFrame frame;
    
    
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
		
		
			graph = GenerateGraph();
            List<Ant> ants = new ArrayList<Ant>();
            List<Path> bbb = new ArrayList<Path>();
            List<Integer> results = new ArrayList<Integer>();
			results.add(MaxLength * Verticles);
            List<Ant> finished = new ArrayList<Ant>();
            Random r = new Random(105);
            for (int j = 0; j < Ants; j++) ants.add(new Ant(r.nextInt(Verticles)));
            double n = 0.0;
            for (int i = 0; i < Iterations; ++i) 
            {
				for(int index=0;index < ants.size();++index)
                {
                    if (ants.get(index).visits == Verticles) // all cities are visited
                    {
                        ants.get(index).lengthOfWay += graph.get(ants.get(index).start_city).get(ants.get(index).way.get(ants.get(index).way.size()-1).to).Length;
                        ants.get(index).way.add(graph.get(ants.get(index).way.get(ants.get(index).way.size()-1).to).get(ants.get(index).start_city));
                        results.add(ants.get(index).lengthOfWay);
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
						Draw(graph, ants, Verticles); // ArrayList<List<Path>> ; ArrayList<Ant> ; int
						ants.get(index).Clear();
                    }
					n = 0.0;
					
					/*for (int k = 0; k < graph.size(); k++){
						for (int j = 0; j < graph.get(k).size(); j++){
							System.out.print(graph.get(k).get(j).to);
						}
						System.out.println();
					}*/
					System.out.println(graph.size() + " " + ants.get(index).position);
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
	
	public static void Draw(List<List<Path>> graph2, List<Ant> ants2, int Verticles){
		
		frame.p.paintBackbone(frame.p.getBetterGraphics(),Verticles,2, graph2, ants2); //TODO Verticles +1 ? Rudy!!!
	}
	
	}