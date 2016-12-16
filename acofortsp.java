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
            for (int i = 0; i <= Program.Verticles; ++i) this.cities.add(false);
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
            for (int i = 0; i <= Program.Verticles; ++i) this.cities.set(i,false);
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
            return Math.pow(this.pheromone, Program.Alpha) *
                   Math.pow(this.InvertedLength, Program.Beta);
        }
    }

public class acofortsp
    {
    public int Ants = 20; // count of ants;
    public int Iterations = 500;
    public int Verticles = 5;
    public int MaxLength = 9;// max length of path
    public double Alpha = 1; // wykladnik
    public double Beta = 1; // wykladnik
    public double Ro = 0.01; // p in (1-p)*pheromone
    public double Q = 2; // adding pheromone
    public double Bonus = 1; // prize for finding better way
    public static List<List<Path>> graph;
	static void Main()
        {
			graph = GenerateGraph();
            int best = ShortestCircuit();
            List<Ant> ants = new ArrayList<Ant>();
            List<Path> bbb = new ArrayList<Path>();
            List<Integer> results = new ArrayList<Integer>();
			results.add(MaxLength * Verticles);
            List<Ant> finished = new ArrayList<Ant>();
            Random r = new Random(105);
            for (int j = 0; j < Ants; j++) ants.add(new Ant(r.nextInt(Verticles)+1));
            double n = 0.0;
            for (int i = 0; i < Iterations; ++i) 
            {
				for(int index=0;index < ants.size();++index)
                {
                    if (ants.get(index).visits == Verticles+1) // all cities are visited
                    {
                        ants.get(index).lengthOfWay += graph[ants.get(index).start_city][ants.get(index).way.Last().to].Length;
                        ants.get(index).way.Add(graph[ants.get(index).way.Last().to][ants.get(index).start_city]);
                        if (ants.get(index).lengthOfWay < results.Min())
							{ 
							bbb = ants.get(index).way; 
							System.out.printl(ants.get(index).lengthOfWay);
							}
                        results.add(ants.get(index).lengthOfWay);
                        double delta = Q / ants.get(index).lengthOfWay;
                        ants.get(index).way.ForEach(path => path.pheromone += Bonus * delta);
                        graph.ForEach(l => l.ForEach(path => path.pheromone *= (1 - Ro)));
                        ants.get(index).Clear();
                    }
                var possible = graph[ants.get(index).position].Where(path => !ants.get(index).cities[path.to]);
                n = possible.Sum((path => path.GetMultiplier())); // :)
                foreach (var path in possible)
                    {
                        if (r.NextDouble() > path.GetMultiplier() / n) continue;
                        ants.get(index).Move(path);
                        ants.get(index).cities[path.to] = true;
                        ants.get(index).visits++;
                        break;
                    }
                }
            }
        }/*
		static int CalcShortPath(int from, int to)
        {
            int[] dist = new int[graph.Count];
            for(int i=0;i<dist.Length;++i) dist[i] = int.MaxValue;
            Queue<int> q = new Queue<int>();
            dist[from] = 0;
            q.Enqueue(from);
            while(q.Count > 0)
            {
                int top = q.Dequeue();
                foreach(Path p in graph[top])
                {
                    if(dist[p.to] > dist[p.from] + p.Length)
                    {
                        dist[p.to] = dist[p.from] + p.Length;
                        q.Enqueue(p.to);
                    }
                }
            }
            return dist[to];
        }
	static int ShortestCircuit()
        {
            int result = int.MaxValue;
            int curr;
            List<int> way;
            List<int> perm = new List<int>();
            for (int i = 1; i <= Verticles; ++i) perm.Add(i);
            var p = new Permutations<int>(perm);
            foreach(var v in p)
            {
                curr = 0;
                way = new List<int>() { 0 };
                way.AddRange(v);
                for (int i = 0; i <= Verticles; ++i) curr += graph[way[i]][way[(i + 1) % Verticles]].Length;
                result = Math.Min(result, curr);
            }
            return result;
        
		}
	static public List<List<Path>> GenerateGraph()
        {
            Random r = new Random();
            List<List<Path>> res = new List<List<Path>>();
            for (int i = 0; i <= Verticles; ++i)
            {
                res.Add(new List<Path>());
                for (int j = 0; j <= Verticles; ++j) 
                {
                    res[i].Add(new Path(-1, -1, -1));
                }
            }
            for (int i = 0; i <= Verticles; ++i)
            {
                for (int j = i; j <= Verticles; ++j)
                {
                    res[i][j] = new Path(i, j, r.Next(1, MaxLength), 1.0 / Verticles);
                }
            }
            for (int i = 0; i <= Verticles; ++i)
            {
                for (int j = 0; j < i; ++j)
                {
                    res[i][j] = new Path(i, j, res[j][i].Length, res[j][i].pheromone);
                }
            }
            //res.ForEach(list => { list.ForEach(path => Console.Write(path.Length)); Console.WriteLine(); });
            return res;

        }
    */
	}