import java.util.*;
import java.*;
public class asrankaco
    {
    static public int Ants = 50; // count of ants;
    static public int Iterations = 1000;
    static public int MaxLength = 9;// max length of path
	static public int Degree = 7;// degree of Rudy's graph
    static public int Verticles = 8 * Degree + 5;
    static public double Alpha = 2.1; // wykladnik
    static public double Beta = 5.61; // wykladnik
    static public double Ro = 0.05; // p in (1-p)*pheromone
    static public double Q = 1 ; // adding pheromone
    static public double Bonus = 1; // prize for finding better way
    static public List<List<Path>> graph;
	public static void start()
        {
			graph = GenerateGraph(Degree, Verticles);
            List<Ant> ants = new ArrayList<Ant>();
            ArrayList<Ant> finished = new ArrayList<Ant>();
            List<Path> bbb = new ArrayList<Path>();
            ArrayList<Integer> results = new ArrayList<Integer>();
			results.add(MaxLength * Verticles);
            Random r = new Random(105);
            for (int j = 0; j < Ants; j++) ants.add(new Ant(0, Verticles));
            double n = 0.0;
            for (int i = 0; i < Iterations; ++i) 
            {
				for(Ant ant : ants)
				{
					if(ant.position == Verticles) finished.add(ant);
                    if(finished.size() == Ants)
                    {
                        ASrank(finished, results);
                        finished = new ArrayList<Ant>();
                    }
					n = 0.0;
					for(Path p : graph.get(ant.position))
					{
						n += p.GetMultiplier();
					}
					for(Path p : graph.get(ant.position))
					{	
						if (r.nextDouble() > p.GetMultiplier() / n) continue;
						ant.Move(p);
						ant.visits++;
						break;
					}  
                }
				Boolean next = true;
				while (next == true){
					try {
						acofortsp.frame.p.getGraphics();
						if (acofortsp.frame.p.getGraphics() != null) next = false;
					}catch(Exception e){
						//System.out.println("");
						next = true;
					}
				}
				Draw(graph, ants, Degree); // ArrayList<List<Path>> ; ArrayList<Ant> ; int
				//ants.get(index).Clear();
            }
			int min = 10000000;
			for(int i=0;i<results.size();++i)
			{
				min = (results.get(i) < min) ? results.get(i) : min;
			}
			//System.out.println(min);
        }
	static void ASrank(ArrayList<Ant> list, ArrayList <Integer> results)
		{
			for(int index = 0; index < list.size(); ++index)
			{
				results.add(list.get(index).lengthOfWay);
			}
			KochamSortowacWJavie(list);
			for(int index = 0; index < list.size(); ++index)
				{
					double delta = Q / list.get(index).lengthOfWay;
					for(int another_index = 0;another_index < list.get(index).way.size(); another_index++)
					{
						list.get(index).way.get(another_index).pheromone += Bonus * (1.1 - index / list.size()) * delta;
					}
				}
			for(int index = 0; index < list.size() ; index++)
				{
					list.get(index).Clear();
				}
			for(int another_index = 0; another_index < graph.size();++another_index)
				{
				for(int one_more_another_index = 0; one_more_another_index < graph.get(another_index).size() ; one_more_another_index++)
					{
						graph.get(another_index).get(one_more_another_index).pheromone *= (1-Ro);
					}
				}
		}
		static void KochamSortowacWJavie(ArrayList<Ant> list)
		{
			Ant qux;
			int minidx;
			for(int i=0;i<list.size();++i)
			{
				minidx = i;
				for(int j=i;j<list.size();++j)
				{
					if(list.get(j).lengthOfWay < list.get(minidx).lengthOfWay) minidx = j;
				}
				qux = list.get(i);
				list.set(i,list.get(minidx));
				list.set(minidx,qux);
			}
		}
		static int CalcShortPath(List<List<Path>> graph, int from, int to)
			{
				int[] dist = new int[graph.size()];
				for(int i=0;i<dist.length;++i) dist[i] = Integer.MAX_VALUE;
				Queue<Integer> q = new LinkedList<Integer>();
				dist[from]=0;
				q.add(from);
				while(!q.isEmpty())
				{
					int top = q.remove();
					for(int index = 0; index < graph.get(top).size(); index++)
					{
						Path p = graph.get(top).get(index);
						if(dist[p.to] > dist[p.from] + p.Length)
						{
							dist[p.to] = dist[p.from] + p.Length;
							q.add(p.to);
						}
					}
				}
			return dist[to];
			}
	static public List<List<Path>> GenerateGraph(int degree, int verticles)
        {
            Random r = new Random();
            List<List<Path>> res = new ArrayList<List<Path>>();
            List<Path> verticle = new ArrayList<Path>();
            for(int i=1;i<=4;++i)   
				{
				verticle.add(new Path(0, i, r.nextInt(MaxLength)+1, 1.0/verticles));
				}
            res.add(verticle);                                                              
            for (int i=0;i<degree;++i)                                                      
            {                                                                               
                for (int j = 0; j < 8; j++) res.add(new ArrayList<Path>());                      
                res.get(8 * i + 2).add(new Path(8 * i + 2, 8 * i + 1 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 3).add(new Path(8 * i + 3, 8 * i + 2 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 4).add(new Path(8 * i + 4, 8 * i + 3 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 5).add(new Path(8 * i + 5, 8 * i + 6 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 6).add(new Path(8 * i + 6, 8 * i + 7 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 7).add(new Path(8 * i + 7, 8 * i + 8 , r.nextInt(MaxLength)+1, 1.0/verticles));// pion
                res.get(8 * i + 5).add(new Path(8 * i + 5, 8 * i + 9 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 6).add(new Path(8 * i + 6, 8 * i + 10, r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 7).add(new Path(8 * i + 7, 8 * i + 11, r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 8).add(new Path(8 * i + 8, 8 * i + 12, r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 1).add(new Path(8 * i + 1, 8 * i + 5 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 2).add(new Path(8 * i + 2, 8 * i + 6 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 3).add(new Path(8 * i + 3, 8 * i + 7 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 4).add(new Path(8 * i + 4, 8 * i + 8 , r.nextInt(MaxLength)+1, 1.0/verticles)); // poziom
                res.get(8 * i + 2).add(new Path(8 * i + 2, 8 * i + 5 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 3).add(new Path(8 * i + 3, 8 * i + 6 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 4).add(new Path(8 * i + 4, 8 * i + 7 , r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 5).add(new Path(8 * i + 5, 8 * i + 10, r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 6).add(new Path(8 * i + 6, 8 * i + 11, r.nextInt(MaxLength)+1, 1.0/verticles));
                res.get(8 * i + 7).add(new Path(8 * i + 7, 8 * i + 12, r.nextInt(MaxLength)+1, 1.0/verticles));   
            }                                                                              
            for (int i = 1; i <= 4; ++i) res.add(new ArrayList<Path>());
			for (int i = 1; i <= 4; ++i) res.get(8*degree+i).add( 
					new Path(8 * degree + i, 8 * degree + 5, r.nextInt(MaxLength)+1, 1.0/verticles));
            
			res.add(new ArrayList<Path>()); // empty list for last verticle
            return res;
		}
	
		public static void Draw(List<List<Path>> graph2, List<Ant> ants2, int Verticles){
			acofortsp.frame.p.paintBackbone(acofortsp.frame.p.getBetterGraphics(), Verticles, graph2, ants2); //TODO Verticles +1 ? Rudy!!!
		}
	
	}