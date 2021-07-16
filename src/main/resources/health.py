#example 02
import requests
from pprint import pprint

THEGRAPH_URL = 'https://api.thegraph.com/index-node/graphql'

graph = THEGRAPH_URL

query = """
{
  indexingStatusForCurrentVersion(subgraphName: "org/example") {
    synced
    health
    fatalError {
      message
      block {
        number
        hash
      }
      handler
    }
    chains {
      network
      chainHeadBlock {
        number
      }
      latestBlock {
        number
      }
    }
  }
}
"""

def run_query(query):
    request = requests.post(graph, json={'query': query})
    if request.status_code == 200:
        return request.json()
    else:
        raise Exception("Query failed to run by returning code of {}. {}".format(request.status_code, query))

result = run_query(query)  # Execute the query
pprint(result)
