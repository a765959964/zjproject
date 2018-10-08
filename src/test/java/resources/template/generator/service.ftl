package ${basePackageService};

import ${basePackageModel}.${modelNameUpperCamel};
import ${basePackage}.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: ${modelNameUpperCamel}Service接口
* @author ${author}
* @date ${date}
*/
public interface ${modelNameUpperCamel}Service extends Service<${modelNameUpperCamel}> {


    List<${modelNameUpperCamel}> getAll(Map map);
}