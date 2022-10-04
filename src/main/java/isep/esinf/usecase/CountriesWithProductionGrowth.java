package isep.esinf.usecase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

/*
 * Alínea 4.
 */
public class CountriesWithProductionGrowth {
  Container data;
  String fruit;

  public CountriesWithProductionGrowth(Container data, String fruit) {
    this.data = data;
    this.fruit = fruit;
  }

  public Map<Integer, ArrayList<String>> execute() {
    Map<Integer, ArrayList<String>> result = new HashMap<>();

    FruitData fruitData = data.getFruitData(fruit);
    Set<String> countries = fruitData.getCountries();

    for (String country : countries) {
      CountryData countryData = fruitData.getCountryData(country);

      int count = 0;
      int max = 0;
      int previousProductionQuantity = 0;

      for (Integer productionQuantity : countryData) {
        if (productionQuantity > previousProductionQuantity) count++;
        else count = 1;

        if (count > max) max = count;

        previousProductionQuantity = productionQuantity;
      }

      ArrayList<String> list = (result.containsKey(max) ? result.get(max) : new ArrayList<>());
      list.add(country);

      result.put(max, list);
    }

    return result;
  }
}
