package com.mscard.mscexcercise.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/** This is a Graph class which can store the vertices and edges */
public class Graph {

	private Map<Vertex, List<Vertex>> adjVertices;

	public Graph() {
		this.adjVertices = new HashMap<>();
	}
	/*Methods to add  vertices*/
	public void addVertex(String label) {
		adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
	}

	/*Method to add edges */
	public void addEdge(String label1, String label2) {
		Vertex v1 = new Vertex(label1);
		Vertex v2 = new Vertex(label2);
		adjVertices.get(v1).add(v2);
		adjVertices.get(v2).add(v1);
	}
	/*Method to get Adjacent  Vertices */
	public List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

	public class Vertex {
		public String label;

		Vertex(String label) {
			this.label = label;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertex other = (Vertex) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}



		@Override
		public String toString() {
			return label;
		}

		private Graph getOuterType() {
			return Graph.this;
		}



		private Graph getEnclosingInstance() {
			return Graph.this;
		}
	}
}
