module.exports = {
  extends: './index.js',
  plugins: ['@typescript-eslint'],
  parser: '@typescript-eslint/parser',
  parserOptions: {
    ecmaVersion: 2018,
    sourceType: 'module',
  },
  settings: {
    // Apply special parsing for TypeScript files
    'import/parsers': {
      '@typescript-eslint/parser': ['.ts', '.d.ts'],
    },
    // Append 'ts' extensions to Airbnb 'import/resolver' setting
    // Original: ['.mjs', '.js', '.json']
    'import/resolver': {
      node: {
        extensions: ['.mjs', '.js', '.json', '.ts', '.d.ts'],
      },
    },
    // Append 'ts' extensions to Airbnb 'import/extensions' setting
    // Original: ['.js', '.mjs', '.jsx']
    'import/extensions': ['.js', '.mjs', '.ts', '.d.ts'],
    // Resolve type definition packages
    'import/external-module-folders': ['node_modules', 'node_modules/@types'],
  },
  rules: {},
};
