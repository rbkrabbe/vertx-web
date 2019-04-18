package io.vertx.ext.web.validation.impl;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.validation.MalformedValueException;
import io.vertx.ext.web.validation.ValueParser;

import java.util.Arrays;

public class SplitterCharArrayParser extends ArrayParser<String> implements ValueParser<String> {

  private String separator;

  public SplitterCharArrayParser(ValueParser<String> itemsParser, String separator) {
    super(itemsParser);
    this.separator = separator;
  }

  @Override
  public JsonArray parse(String serialized) throws MalformedValueException {
    return Arrays
      .stream(serialized.split(separator, -1))
      .map(this::parseValue)
      .reduce(new JsonArray(), JsonArray::add, JsonArray::addAll);
  }

  @Override
  protected boolean isSerializedEmpty(String serialized) {
    return serialized.isEmpty();
  }
}
