package classicaco;

import java.awt.*;
import java.awt.List;
import java.util.*;
import java.util.Queue;

/*classic ACO*/
 class Ant
    {
        public int position;// current verticle
        public int lengthOfWay;
        public ArrayList<Path> way; // list of visited verticles
        public Ant(int pos)
        {
        	
            position = pos;
            way = new ArrayList<Path>();
            lengthOfWay = 0;
        }
        public void Move(Path p)
        {
            this.position = p.to;
            this.lengthOfWay += p.Length;
            this.way.add(p);
        }
        public void Clear()
        {
            position = 0;
            way = new ArrayList<Path>();
            lengthOfWay = 0;
        }
    }


class GraphGen {
	static public List<List<Path>> GenerateGraph(int degree, int verticles)
        {
            Random r = new Random();
            List<List<Path>> res = new ArrayList<ArrayList<Path>>();
            ArrayList<Path> verticle = new ArrayList<Path>();
            for(int i=1;i<=4;++i)      verticle.add(new Path(0, i, (r.nextInt(classicaco.MaxLength)+ 1), 1.0/verticles));
            res.add(verticle);                                                              
            for (int i=0;i<degree;++i)                                                      
            {                                                                               
                for (int j = 0; j < 8; j++) res.add(new ArrayList<Path>());                      
                res[8 * i + 2].add(new Path(8 * i + 2, 8 * i + 1 ,( r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 3].add(new Path(8 * i + 3, 8 * i + 2 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 4].add(new Path(8 * i + 4, 8 * i + 3 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 5].add(new Path(8 * i + 5, 8 * i + 6 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 6].add(new Path(8 * i + 6, 8 * i + 7 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 7].add(new Path(8 * i + 7, 8 * i + 8 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));// pion
                res[8 * i + 5].add(new Path(8 * i + 5, 8 * i + 9 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 6].add(new Path(8 * i + 6, 8 * i + 10, (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 7].add(new Path(8 * i + 7, 8 * i + 11, (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 8].add(new Path(8 * i + 8, 8 * i + 12, (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 1].add(new Path(8 * i + 1, 8 * i + 5 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 2].add(new Path(8 * i + 2, 8 * i + 6 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 3].add(new Path(8 * i + 3, 8 * i + 7 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 4].add(new Path(8 * i + 4, 8 * i + 8 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles)); // poziom
                res[8 * i + 2].add(new Path(8 * i + 2, 8 * i + 5 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 3].add(new Path(8 * i + 3, 8 * i + 6 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 4].add(new Path(8 * i + 4, 8 * i + 7 , (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 5].add(new Path(8 * i + 5, 8 * i + 10, (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 6].add(new Path(8 * i + 6, 8 * i + 11, (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));
                res[8 * i + 7].add(new Path(8 * i + 7, 8 * i + 12, (r.nextInt(classicaco.MaxLength))+1, 1.0/verticles));   
            }                                                                              
            for (int i = 1; i <= 4; ++i){ 
            	res.add(new ArrayList<Path>());                  
                new Path(8 * degree + i, 8 * degree + 5, r.nextInt(classicaco.MaxLength)+1, 1.0/verticles);
                };
            res.add(new ArrayList<Path>()); // empty list for last verticle
            return res;
        }
}
class Dijkstra
{
    static int CalcShortPath( int from, int to)
    {
    	int MaxValue;
        int[] dist = new int[classicaco.graph.size];
        for(int i=0;i<dist.length;++i) dist[i] = MaxValue;
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(); 
        dist[from] = 0;
        q.add(from);
        while(!q.isEmpty())
        {
            int top = q.remove();
            for(Path p : classicaco.graph[top])
            {
                if(dist[p.to] > dist[p.from] + p.Length)
                {
                    dist[p.to] = dist[p.from] + p.Length;
                    q.add(p.to);
                }
            }
        }
        return dist[to];
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
    	_length = l;
        InvertedLength = 1.0 / l;
    	ph = 0;
        from = s;
        to = e;
        pheromone = ph;
        Length = l;
    }
    public double GetMultiplier()
    {
        return Math.pow(this.pheromone, classicaco.Alpha) *
               Math.pow(InvertedLength, classicaco.Beta);
    }
}
public class classicaco
{
    public int Ants = 200; // count of ants;
    public int Iterations = 100000;
    public static int Degree = 1000;// degree of Rudy's graph
    public static int Verticles = 8 * Degree + 5;
    public static int MaxLength = 10;// max length of path
    public static double Alpha = 1.34; // pheromone
    public static double Beta = 9.7; // length
    public double Ro = 0.3; // evaporating
    public double Q = 2.14; // adding pheromone
    public double Bonus = 7.3; // prize for finding better way
    public static List<List<Path>> graph; 

    static void main(String[] args)
    {
    	graph = GraphGen.GenerateGraph(Degree, Verticles);
        int best = Dijkstra.CalcShortPath( 0, Verticles);
        ArrayList<Ant> ants = new ArrayList<Ant>();
        ArrayList<Integer> results = new ArrayList<int>();
        results.add(classicaco.MaxLength * Verticles);
        for (int j = 0; j < Ants; j++)  ants.add(new Ant());
        double n = 0.0;
        Random r = new Random(105);
        for (int i = 0; i < Iterations; ++i) 
        {
            for( var ant : ants)
            {
                if (ant.position == Verticles)  // in last verticle, move to start and update pheromones
                {
                    var delta = Q / ant.lengthOfWay;
                    if (results.Min() > ant.lengthOfWay) ant.way.ForEach(path -> path.pheromone += Bonus * delta);
                    else ant.way.ForEach(path -> path.pheromone += delta);
                    results.add(ant.lengthOfWay);
               //     Console.WriteLine("{0}#: {1}", results.Count, ant.lengthOfWay);
                    ant.Clear();
                    graph.ForEach(list -> list.ForEach(path -> path.pheromone *= (1 - Ro)));
                    graph.ForEach(list -> list.ForEach(path -> path.pheromone = (path.pheromone < 1.0 / Verticles ? 1.0 / Verticles : path.pheromone)));
                }
                n = graph[ant.position].Sum((path -> path.GetMultiplier())); // :)
                for(var path : graph[ant.position])
                {
                    if (r.NextDouble() > path.GetMultiplier() / n) continue;
                    ant.Move(path);
                    break;
                }
            }
        }
      
        Console.Read();
    }
    static double diff(int res, int best)
    {
        return Math.abs(best - res)* 1.0 / res;
    }
}
