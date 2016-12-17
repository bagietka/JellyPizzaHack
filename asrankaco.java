import java.util.*;
import java.*;

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
            for (int i = 0; i <= asrankaco.Verticles; ++i) this.cities.add(false);
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
            for (int i = 0; i <= asrankaco.Verticles; ++i) this.cities.set(i,false);
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
            return Math.pow(this.pheromone, asrankaco.Alpha) *
                   Math.pow(this.InvertedLength, asrankaco.Beta);
        }
    }
class CompareTo implements Comparator <Path>
	{
	@Override
	public int compare(Path p1, Path p2)
		{
			return (p2.Length - p1.Length);
		}
	}
public class asrankaco
    {
    static public int Ants = 20; // count of ants;
    static public int Iterations = 500;
    static public int Verticles = 5;
    static public int MaxLength = 9;// max length of path
	static public int Degree = 500;// degree of Rudy's graph
    static public double Alpha = 1; // wykladnik
    static public double Beta = 1; // wykladnik
    static public double Ro = 0.01; // p in (1-p)*pheromone
    static public double Q = 2; // adding pheromone
    static public double Bonus = 1; // prize for finding better way
    static public ArrayList<ArrayList<Path>> graph;
	public static void main(String[] args)
        {
			graph = GenerateGraph(Degree, Verticles);
            List<Ant> ants = new ArrayList<Ant>();
            ArrayList<Ant> finished = new ArrayList<Ant>();
            List<Path> bbb = new ArrayList<Path>();
            ArrayList<Integer> results = new ArrayList<Integer>();
			results.add(MaxLength * Verticles);
            Random r = new Random(105);
            for (int j = 0; j < Ants; j++) ants.add(new Ant(r.nextInt(Verticles)+1));
            double n = 0.0;
            for (int i = 0; i < Iterations; ++i) 
            {
				for(int index=0;index < ants.size();++index)
                {
					if(ants.get(index).position == Verticles) finished.add(ants.get(index));
                    if(finished.size() == Ants)
                    {
                        ASrank(finished, results);
                        finished = new ArrayList<Ant>();
                    }
					n = 0.0;
                    for(int another_index=0;another_index < graph.get(ants.get(index).position).size() ; another_index++)
					{
						if(!ants.get(index).cities.get(// kopiowane, ciekawe czy wejdzie
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
			int min = 10000000;
			for(int i=0;i<results.size();++i)
			{
				min = (results.get(i) < min) ? results.get(i) : min;
			}
			System.out.println(min);
        }
	static void ASrank(ArrayList<Ant> list, ArrayList <Integer> results)
		{
			for(int index = 0; index < list.size(); ++index)
			{
				results.add(list.get(index).lengthOfWay);
			}
			//results.AddRange(list.Select(ant => ant.lengthOfWay));
			Collections.sort(list, new CompareTo());
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
				//list.ForEach(ant => ant.Clear());
			for(int another_index = 0; another_index < graph.size();++another_index)
				{
				for(int one_more_another_index = 0; one_more_another_index < graph.get(another_index).size() ; one_more_another_index++)
					{
						graph.get(another_index).get(one_more_another_index).pheromone *= (1-Ro);
					}
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
	static public ArrayList<ArrayList<Path>> GenerateGraph(int degree, int verticles)
        {
            Random r = new Random();
            ArrayList<ArrayList<Path>> res = new ArrayList<ArrayList<Path>>();
            ArrayList<Path> verticle = new ArrayList<Path>();
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
			for (int i = 1; i <= 4; ++i) res.get(8*degree+1).add( 
					new Path(8 * degree + i, 8 * degree + 5, r.nextInt(MaxLength)+1, 1.0/verticles));
            
			res.add(new ArrayList<Path>()); // empty list for last verticle
            return res;
		}
	}