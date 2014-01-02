package it.corley.ant;

import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.model.DeleteAttributesRequest;
import org.apache.tools.ant.BuildException;

public class SimpleDBDeleteTask extends SimpleDB {

  private String itemName;

  @Override
  public void execute() {
    validateConfiguration();

    AmazonSimpleDB simpleDB = getAmazonSimpleDB();
    DeleteAttributesRequest deleteRequest = getDeleteAttributesRequest(itemName);
    simpleDB.deleteAttributes(deleteRequest);
  }

  private void validateConfiguration() {
    if (itemName == null || itemName.length() == 0) {
      throw new BuildException("Missing value for parameter 'itemName'");
    }
  }

  private DeleteAttributesRequest getDeleteAttributesRequest(String itemName) {
    DeleteAttributesRequest request = new DeleteAttributesRequest();
    request.setDomainName(getDomain());
    request.setItemName(itemName);
    return request;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
}
