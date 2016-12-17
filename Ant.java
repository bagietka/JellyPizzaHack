import java.util.*;
public class Ant
    {
        public int position;
        public int lengthOfWay;
        public List<Path> way; // list of visited verticles
        public List<Boolean> cities;
        public int visits;
        public int start_city;
		private int V;
        public Ant(int pos, int verticles)
        {
            this.position = pos;
            this.start_city = pos;
            this.way = new ArrayList<Path>();
            this.visits = 1;
            this.lengthOfWay = 0;
            this.cities = new ArrayList<Boolean>();
			this.V = verticles;
            for (int i = 0; i < V; ++i) this.cities.add(false);
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
            for (int i = 0; i < V; ++i) this.cities.set(i,false);
            this.cities.set(position,true);
        }
    }